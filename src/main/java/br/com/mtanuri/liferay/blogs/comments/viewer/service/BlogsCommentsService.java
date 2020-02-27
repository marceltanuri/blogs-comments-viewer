package br.com.mtanuri.liferay.blogs.comments.viewer.service;

import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBMessageLocalService;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import br.com.mtanuri.liferay.blogs.comments.viewer.model.BlogComment;

public class BlogsCommentsService {

	public static List<BlogComment> getBlogComments(long blogId, MBThreadLocalService _mbThreadLocalService,
			MBMessageLocalService _mbMessageLocalService, UserLocalService _userLocalService) throws PortalException {

		DynamicQuery query = _mbThreadLocalService.dynamicQuery();
		query.add(RestrictionsFactoryUtil.eq("title", String.valueOf(blogId)));
		List<MBThread> threads = _mbThreadLocalService.dynamicQuery(query);

		if (threads != null && !threads.isEmpty()) {
			List<BlogComment> comments = new ArrayList<BlogComment>();

			MBThread mbThread = threads.get(0);
			List<MBMessage> threadMessages = _mbMessageLocalService.getThreadMessages(mbThread.getThreadId(), 0);
			long parentMessageId = 0l;

			for (MBMessage comment : threadMessages) {

				if (comment.getBody().equals(String.valueOf(blogId))) {
					parentMessageId = comment.getMessageId();
					continue;
				}

				comment.getBody();
				comment.getCreateDate();
				User user = _userLocalService.getUser(comment.getUserId());

				SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				sdfAmerica.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
				String commentFormatDate = sdfAmerica.format(comment.getCreateDate());

				String answerOf = String
						.valueOf(comment.getParentMessageId() == parentMessageId ? 0 : comment.getParentMessageId());

				comments.add(new BlogComment(String.valueOf(comment.getMessageId()), user.getFullName(),
						commentFormatDate, comment.getBody(), answerOf));

			}

			return comments;
		}

		return null;
	}

}
