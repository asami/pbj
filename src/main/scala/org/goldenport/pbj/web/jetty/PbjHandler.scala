package org.goldenport.pbj.web.jetty

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Cookie
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.AbstractHandler
import org.goldenport.pbj.Pbj
import org.goldenport.kaleidox.http._

/*
 * @since   Mar.  2, 2021
 * @version Mar. 24, 2021
 * @author  ASAMI, Tomoharu
 */
class PbjHandler(pbj: Pbj) extends AbstractHandler {
  private val _engine = pbj.createKaleidox()

  @throws(classOf[IOException])
  @throws(classOf[ServletException])
  def handle(
    target: String,
    baseRequest: Request,
    request: HttpServletRequest,
    response: HttpServletResponse
  ): Unit = {
    val req = ServletHttpRequest(request)
    val res = ServletHttpResponse(response)
    val r = _engine.execute(req, res)
    response.setStatus(r.status)
    response.setContentType(r.mimeType)
    response.setCharacterEncoding(r.charset.name)
    for (h <- r.headers)
      response.setHeader(h.key, h.encodedValue)
    for (c <- r.cookies)
      response.addCookie(new Cookie(c.key, c.value))
    val out = response.getOutputStream()
    r.writeContent(out)
    baseRequest.setHandled(true)
  }
}
