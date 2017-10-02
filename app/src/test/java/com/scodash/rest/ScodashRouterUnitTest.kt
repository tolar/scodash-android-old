package com.scodash.rest

import com.scodash.BuildConfig
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations


class ScodashRouterUnitTest {

	lateinit var scodashRouter: ScodashRouter


	@Before
	@Throws(Exception::class)
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
		scodashRouter = ScodashRouter()
	}


	@Test
	fun getScoreDashListTest() {

		if (BuildConfig.FLAVOR.startsWith("mock")) {
			scodashRouter.getScoreDashList(123)
					.subscribe(
							{ response ->
								Assert.assertNotNull(response)
								val scoreDash = response.body()

								Assert.assertNotNull(scoreDash)
								Assert.assertEquals(scoreDash!!.description, "Dash board of my team")
								Assert.assertEquals(scoreDash.name, "MyTeam")
								Assert.assertEquals(scoreDash.ownerName, "Orlando")
								Assert.assertEquals(scoreDash.ownerEmail, "orlando@email.com")
								Assert.assertNotNull(scoreDash.itemList)
								Assert.assertEquals(scoreDash.itemList.size, 3)
								Assert.assertEquals(scoreDash.itemList[2].name, "Lojza")
								Assert.assertEquals(scoreDash.itemList[2].score, 0)

							},
							{ error ->
								Assert.fail(error.message)
							}
					)


		} else {
			fail("Do not execute ScodashRouterUnitTest outside the mock flavour!")
		}


	}
}
