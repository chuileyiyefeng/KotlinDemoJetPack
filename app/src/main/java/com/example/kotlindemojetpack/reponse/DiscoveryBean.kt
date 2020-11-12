package com.example.kotlindemojetpack.reponse

/**
 *  create by pan yi on 2020/11/6
 *  desc :
 */
data class DiscoveryBean(
    val adExist: Boolean,
    val count: Int,
    val itemList: ArrayList<Item>,
    val nextPageUrl: String?,
    val total: Int
)

data class Item(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class Data(
    val actionUrl: Any,
    val adTrack: List<Any>,
    val autoPlay: Boolean,
    val content: Content,
    val count: Int,
    val dataType: String,
    val description: String,
    val expert: Boolean,
    val follow: Any,
    val footer: Any,
    val haveReward: Boolean,
    val header: Header,
    val icon: String,
    val iconType: String,
    val id: Int,
    val ifNewest: Boolean,
    val ifPgc: Boolean,
    val ifShowNotificationIcon: Boolean,
    val image: String,
    val itemList: List<ItemX>,
    val label: LabelXX,
    val labelList: List<Any>,
    val medalIcon: Boolean,
    val newestEndTime: Any,
    val rightText: String,
    val shade: Boolean,
    val subTitle: Any,
    val switchStatus: Boolean,
    val text: String,
    val title: String,
    val type: String,
    val uid: Int
)

data class Content(
    val adIndex: Int,
    val `data`: DataX,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class Header(
    val actionUrl: Any,
    val cover: Any,
    val description: String,
    val font: Any,
    val icon: String,
    val iconType: String,
    val id: Int,
    val label: Any,
    val labelList: Any,
    val rightText: Any,
    val showHateVideo: Boolean,
    val subTitle: Any,
    val subTitleFont: Any,
    val textAlign: String,
    val time: Any,
    val title: String
)

data class ItemX(
    val adIndex: Int,
    val `data`: DataXX,
    val id: Int,
    val tag: Any,
    val trackingData: Any,
    val type: String
)

data class LabelXX(
    val card: String,
    val detail: Any,
    val text: String
)

data class DataX(
    val ad: Boolean,
    val adTrack: List<Any>,
    val author: Any,
    val brandWebsiteInfo: Any,
    val campaign: Any,
    val category: String,
    val collected: Boolean,
    val consumption: Consumption,
    val cover: Cover,
    val dataType: String,
    val date: Long,
    val description: String,
    val descriptionEditor: String,
    val descriptionPgc: String,
    val duration: Int,
    val favoriteAdTrack: Any,
    val id: Int,
    val idx: Int,
    val ifLimitVideo: Boolean,
    val label: Any,
    val labelList: Any,
    val lastViewTime: Any,
    val library: String,
    val playInfo: List<Any>,
    val playUrl: String,
    val played: Boolean,
    val playlists: Any,
    val promotion: Any,
    val provider: Provider,
    val reallyCollected: Boolean,
    val recallSource: Any,
    val recall_source: Any,
    val releaseTime: Long,
    val remark: Any,
    val resourceType: String,
    val searchWeight: Int,
    val shareAdTrack: Any,
    val slogan: String,
    val src: Any,
    val subtitles: List<Any>,
    val tags: List<Any>,
    val thumbPlayUrl: String,
    val title: String,
    val titlePgc: String,
    val type: String,
    val videoPosterBean: Any,
    val waterMarks: Any,
    val webAdTrack: Any,
    val webUrl: WebUrl
)

data class Consumption(
    val collectionCount: Int,
    val realCollectionCount: Int,
    val replyCount: Int,
    val shareCount: Int
)

data class Cover(
    val blurred: String,
    val detail: String,
    val feed: String,
    val homepage: String,
    val sharing: Any
)

data class Provider(
    val alias: String,
    val icon: String,
    val name: String
)

data class WebUrl(
    val forWeibo: String,
    val raw: String
)

data class DataXX(
    val actionUrl: String,
    val adTrack: List<Any>,
    val autoPlay: Boolean,
    val dataType: String,
    val description: String,
    val header: HeaderX,
    val id: Int,
    val image: String,
    val label: Label,
    val labelList: List<LabelX>,
    val shade: Boolean,
    val title: String
)

data class HeaderX(
    val actionUrl: Any,
    val cover: Any,
    val description: Any,
    val font: Any,
    val icon: Any,
    val id: Int,
    val label: Any,
    val labelList: Any,
    val rightText: Any,
    val subTitle: Any,
    val subTitleFont: Any,
    val textAlign: String,
    val title: Any
)

data class Label(
    val card: String,
    val detail: Any,
    val text: String
)

data class LabelX(
    val actionUrl: Any,
    val text: String
)