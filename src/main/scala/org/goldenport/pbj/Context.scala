package org.goldenport.pbj

import org.goldenport.RAISE
import org.goldenport.i18n.I18NString
import org.goldenport.cli.{Config => CliConfig, Environment}
import org.goldenport.recorder.{ForwardRecorder, Recorder}

/*
 * @since   Jan. 11, 2021
 * @version Jan. 11, 2021
 * @author  ASAMI, Tomoharu
 */
case class Context(
  val environment: Environment,
  val config: Config
) extends Environment.AppEnvironment with ForwardRecorder {
  protected def forward_Recorder: Recorder = recorder

  def recorder = environment.recorder
  def isPlatformWindows: Boolean = environment.isPlatformWindows
}
