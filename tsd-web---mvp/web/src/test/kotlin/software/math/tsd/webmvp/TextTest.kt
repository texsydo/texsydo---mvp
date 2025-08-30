package software.math.tsd.webmvp

import arrow.core.none
import arrow.core.some
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TextTest : StringSpec({
    "should create an article with a cover" {
        val article = Article(
            title = Title("Test"),
            cover = Cover("cover.png").some(),
            abstract = Abstract("Abstract here"),
            content = emptyList()
        )

        article.title.title shouldBe "Test"
        article.cover.getOrNull()?.title shouldBe "cover.png"
        article.abstract.title shouldBe "Abstract here"
        article.content shouldBe emptyList()
    }

    "should create an article without a cover" {
        val article = Article(
            title = Title("NoCover"),
            cover = none(),
            abstract = Abstract("Abstract"),
            content = emptyList()
        )

        article.cover.isNone() shouldBe true
    }

    "should allow nested sub-articles" {
        val child = Article(
            title = Title("Child"),
            cover = none(),
            abstract = Abstract("Child abstract"),
            content = listOf(Leaf("Child content"))
        )

        val parent = Article(
            title = Title("Parent"),
            cover = none(),
            abstract = Abstract("Parent abstract"),
            content = listOf(SubArticle(child))
        )

        val firstNode = parent.content.first() as SubArticle
        firstNode.article.title.title shouldBe "Child"
    }

    "should allow mixed leaf and sub-article content" {
        val leaf = Leaf("Hello world")
        val child = Article(
            title = Title("Nested"),
            cover = none(),
            abstract = Abstract("Nested abstract"),
            content = emptyList()
        )

        val article = Article(
            title = Title("Root"),
            cover = none(),
            abstract = Abstract("Root abstract"),
            content = listOf(leaf, SubArticle(child))
        )

        (article.content[0] as Leaf<*>).content shouldBe "Hello world"
        (article.content[1] as SubArticle).article.title.title shouldBe "Nested"
    }
})