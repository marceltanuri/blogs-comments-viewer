package br.com.mtanuri.liferay.blogs.comments.viewer.portlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.message.boards.service.MBMessageLocalService;
import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import br.com.mtanuri.liferay.blogs.comments.viewer.constants.BlogsCommentsViewerPortletKeys;
import br.com.mtanuri.liferay.blogs.comments.viewer.model.BlogComment;
import br.com.mtanuri.liferay.blogs.comments.viewer.service.BlogsCommentsService;

/**
 * @author marceltanuri
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.collaboration",
		"com.liferay.portlet.instanceable=true", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + BlogsCommentsViewerPortletKeys.BlogsCommentsViewer,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class BlogsCommentsViewerPortlet extends MVCPortlet {
	@Reference
	MBThreadLocalService _mbThreadLocalService;

	@Reference
	MBMessageLocalService _mbMessageLocalService;

	@Reference
	UserLocalService _userLocalService;

	@Reference
	BlogsEntryLocalService _blogsEntryLocalService;

	public void getBlogComments(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {

		String blogId = ParamUtil.getString(request, "blogId");

		List<BlogComment> blogComments = BlogsCommentsService.getBlogComments(Long.valueOf(blogId),
				_mbThreadLocalService, _mbMessageLocalService, _userLocalService);

		Type listType = new TypeToken<List<BlogComment>>() {
		}.getType();

		Gson gson = new Gson();
		String json = gson.toJson(blogComments, listType);

		request.setAttribute("blogComments", json);
		request.setAttribute("blogId", blogId);

	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		List<BlogsEntry> blogsEntries = _blogsEntryLocalService.getBlogsEntries(-1, -1);

		Type listType = new TypeToken<List<BlogsEntry>>() {
		}.getType();

		Gson gson = new Gson();
		String json = gson.toJson(blogsEntries, listType);

		renderRequest.setAttribute("blogPosts", json);
		if (renderRequest.getAttribute("blogComments") == null) {
			renderRequest.setAttribute("blogComments", "null");
		}

		super.render(renderRequest, renderResponse);

	}

}