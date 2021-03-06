package com.github.winteryoung.yanwte.spring.internals

import com.github.winteryoung.yanwte.YanwteException
import com.github.winteryoung.yanwte.YanwtePlugin
import org.springframework.context.ApplicationContext

/**
 * This plugin implementation supports integrating Spring.
 *
 * @author Winter Young
 * @since 2016/1/23
 */
internal class SpringPlugin(val applicationContext: ApplicationContext) : YanwtePlugin {
    override fun getExtensionByName(extensionName: String): Any? {
        val extensionClass = applicationContext.classLoader.let { cl ->
            try {
                cl.loadClass(extensionName)
            } catch (e: ClassNotFoundException) {
                return null
            }
        }

        applicationContext.getBeansOfType(extensionClass).values.toList().let { extensions ->
            if (extensions.isEmpty()) {
                return null
            }

            if (extensions.size > 1) {
                throw YanwteException("Cannot find a unique bean with type $extensionName")
            }

            return extensions[0]
        }
    }

    override fun toString(): String {
        return "SpringPlugin()"
    }
}