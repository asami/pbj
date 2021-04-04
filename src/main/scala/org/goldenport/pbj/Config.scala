package org.goldenport.pbj

import org.goldenport.RAISE
import org.goldenport.i18n.I18NString
import org.goldenport.cli.{Config => CliConfig, Environment}
import org.goldenport.value._

/*
 * @since   Jan. 11, 2021
 * @version Jan. 11, 2021
 * @author  ASAMI, Tomoharu
 */
case class Config(
  cliConfig: CliConfig,
  isLocation: Boolean = true
) {
}

object Config {
  def create(env: Environment): Config = {
    Config(
      env.config
    )
  }
}
