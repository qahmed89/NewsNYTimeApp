package com.ahmed.articleList.data.datasource.mapper

import com.ahmed.articleList.data.datasource.model.ArticleDomainModel
import com.ahmed.articleList.data.datasource.model.MediaDataModel
import com.ahmed.articleList.domain.model.MediaDomainModel
import com.ahmed.articleList.data.datasource.model.MediaMetadataDataModel
import com.ahmed.articleList.data.datasource.model.MediaMetadataDomainModel
import com.ahmed.articleList.data.datasource.model.MostPopularDataModel
import com.ahmed.articleList.data.datasource.model.MostPopularDomainModel
import com.ahmed.articleList.data.datasource.model.ResultDataModel


fun MostPopularDataModel?.toMostPopularDomainModel() = MostPopularDomainModel(
    results = this?.results?.map { it.toArticleDomainModel() }.orEmpty()
)

private fun ResultDataModel?.toArticleDomainModel() = ArticleDomainModel(
    title = this?.title.orEmpty(),
    source = this?.source.orEmpty(),
    media = this?.media?.toListMediaDomainModel().orEmpty(),
    publishedDate = this?.publishedDate.orEmpty(),
    section = this?.section.orEmpty(),
    subsection = this?.subsection.orEmpty(),
    type = this?.type.orEmpty(),
    byline = this?.byline.orEmpty(),
    updated = this?.updated.orEmpty(),
    abstract = this?.abstract.orEmpty(),
    url = this?.url.orEmpty(),
    uri = this?.uri.orEmpty(),

    )

private fun List<MediaDataModel>.toListMediaDomainModel() = this.map { it.toMediaDomainModel() }
private fun MediaDataModel?.toMediaDomainModel() = MediaDomainModel(
    caption = this?.caption.orEmpty(),
    copyright = this?.copyright.orEmpty(),
    mediaMetadata = this?.mediaMetadata?.map { it.toMediaMetadataDomainModel() }.orEmpty()

)

private fun MediaMetadataDataModel?.toMediaMetadataDomainModel() = MediaMetadataDomainModel(
    format = this?.format.orEmpty(),
    width = this?.width ?: 0,
    height = this?.height ?: 0,
    url = this?.url.orEmpty()
)