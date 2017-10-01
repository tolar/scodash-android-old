package com.scodash.rest

import com.scodash.BuildConfig
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations


class ScodashRouterUnitTest {

	lateinit var scodashRouter: ScodashRouter


	@Before
	@Throws(Exception::class)
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		scodashRouter = ScodashRouter()
	}


	@Test
	fun getScoreDashListTest() {

		if (BuildConfig.FLAVOR.startsWith("mock")) {
			val ret = scodashRouter.getScoreDashList(123)
			assertNotNull(ret)
		} else {
			assertTrue(true)
		}


	}
}
