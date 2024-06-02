package com.ahmed.articleList.data.datasource.mock

import com.ahmed.articleList.data.datasource.model.ArticleDomainModel
import com.ahmed.articleList.data.datasource.model.MediaDataModel
import com.ahmed.articleList.data.datasource.model.MediaMetadataDataModel
import com.ahmed.articleList.data.datasource.model.MediaMetadataDomainModel
import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel
import com.ahmed.articleList.data.datasource.model.ResultDataModel
import com.ahmed.articleList.domain.model.MediaDomainModel
import com.ahmed.articleList.presentation.model.Article

object MostPopularMocking {
    val fakeMostPopularDataModel = MostPopularDataModel(
        copyright = "Copyright 2023 The New York Times Company",
        numResults = 20,
        status = "ok",
        results = listOf(
            ResultDataModel(
                `abstract` = "This is a fake abstract for a New York Times article.",
                adxKeywords = "fake, news, article",
                assetId = 1234567890,
                byline = "By John Smith",
                column = Any(), // Replace with actual column data if available
                desFacet = listOf("Fake", "News"),
                etaId = 0,
                geoFacet = listOf("United States"),
                id = 9876543210,
                media = listOf(
                    MediaDataModel(
                        type = "image",
                        subtype = "photo",
                        caption = "A fake photo for the fake article.",
                        copyright = "Fake Copyright Holder",
                        approvedForSyndication = 1,
                        mediaMetadata = listOf(
                            MediaMetadataDataModel(
                                url = "https://example.com/fake_image.jpg",
                                format = "Standard Thumbnail",
                                height = 75,
                                width = 75
                            )
                        )
                    )
                ),
                nytdsection = "fake_section",
                orgFacet = listOf("Fake News Organization"),
                perFacet = listOf(),
                publishedDate = "2023-05-27",
                section = "Fake Section",
                source = "Fake News Source",
                subsection = "Fake Subsection",
                title = "This is a Fake Headline",
                type = "Article",
                updated = "2023-05-27",
                uri = "nyt://article/fake_article_id",
                url = "https://www.nytimes.com/fake_article_url"
            )))


    val fakeMostPopularDomainModel = MostPopularDomainModel(
        results = listOf(
            ArticleDomainModel(
                `abstract` = "This is a fake abstract for a New York Times article.",
                byline = "By John Smith",
                media = listOf(
                    MediaDomainModel(

                        caption = "A fake photo for the fake article.",
                        copyright = "Fake Copyright Holder",
                        mediaMetadata = listOf(
                            MediaMetadataDomainModel(
                                url = "https://example.com/fake_image.jpg",
                                format = "Standard Thumbnail",
                                height = 75,
                                width = 75
                            )
                        )
                    )
                ),
                publishedDate = "2023-05-27",
                section = "Fake Section",
                source = "Fake News Source",
                subsection = "Fake Subsection",
                title = "This is a Fake Headline",
                type = "Article",
                updated = "2023-05-27",
                uri = "nyt://article/fake_article_id",
                url = "https://www.nytimes.com/fake_article_url"
            )))

   val fakeArticleData = Article(
       uri = "nyt://article/fake_article_id",
       url = "https://www.nytimes.com/fake_article_url",
       imageUrl = "https://example.com/fake_image.jpg",
       abstract = "This is a fake abstract for a New York Times article.",
       date = "By John Smith",
       title = "This is a Fake Headline",
       author = "By John Smith"
   )
}