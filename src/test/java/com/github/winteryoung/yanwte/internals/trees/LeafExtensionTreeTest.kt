package com.github.winteryoung.yanwte.internals.trees

import com.github.winteryoung.yanwte.ExtensionPointInput
import com.github.winteryoung.yanwte.ExtensionPointOutput
import com.github.winteryoung.yanwte.internals.ExtensionExecution
import com.github.winteryoung.yanwte.internals.YanwteExtension
import org.junit.Assert
import org.junit.Test

/**
 * @author Winter Young
 * @since 2016/1/19
 */
class LeafExtensionTreeTest {
    @Test
    fun testNormal() {
        val node = LeafExtensionTree("testExtPoint", YanwteExtension("testExt", ExtensionExecution { input ->
            val (args) = input
            val (arg) = args
            ExtensionPointOutput(arg as Int * 3)
        }))
        val (output) = node(ExtensionPointInput(listOf(3)))

        Assert.assertEquals(9, output)
    }
}