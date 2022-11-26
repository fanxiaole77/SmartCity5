package com.example.myapplication.network

data class Login(
    val password: String,
    val username: String
)

data class Message(
    val code: Int,
    val msg: String,
    val token: String
)

data class Register(
    val avatar: String,
    val email: String,
    val idCard: String,
    val nickName: String,
    val password: String,
    val phonenumber: String,
    val sex: String,
    val userName: String
)

data class HomeBanner(
    val code: String,
    val msg: String,
    val rows: List<Row>,
    val total: String
) {
    data class Row(
        val advImg: String,
        val advTitle: String,
        val id: Int,
        val servModule: String,
        val sort: Int,
        val targetId: Int,
        val type: String
    )
}

data class HomeService(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val id: Int,
        val imgUrl: String,
        val isRecommend: String,
        val link: String,
        val serviceDesc: String,
        val serviceName: String,
        val serviceType: String,
        val sort: Int
    )
}



data class NewsType(
    val code: Int,
    val msg: String,
    val `data`: List<Row>,
    val total: Int
) {
    data class Row(
        val id: Int,
        val imgUrl: String,
        val name: String,
        val link: String,
        val serviceDesc: String,
        val serviceName: String,
        val serviceType: String,
        val sort: Int
    )
}
data class NewsLIst(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val commentNum: Int,
        val content: String,
        val cover: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val publishDate: String,
        val readNum: Int,
        val status: String,
        val subTitle: String,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String
    )
}


data class NewsContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val appType: String,
        val commentNum: Any,
        val content: String,
        val cover: String,
        val hot: String,
        val id: Int,
        val likeNum: Int,
        val publishDate: String,
        val readNum: Any,
        val status: String,
        val subTitle: String,
        val tags: Any,
        val title: String,
        val top: String,
        val type: String
    )
}

data class UserInfo(
    val code: Int,
    val msg: String,
    val user: User
) {
    data class User(
        val avatar: String,
        val balance: Int,
        val email: String,
        val idCard: String,
        val nickName: String,
        val phonenumber: String,
        val score: Int,
        val sex: String,
        val userId: Int,
        val userName: String
    )
}

data class User(
    val email: String,
    val idCard: String,
    val nickName: String,
    val phonenumber: String,
    val sex: String
)


data class Pass(
    val newPassword: String,
    val oldPassword: String
)

data class LitterBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LitterNewsType(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LitterNewsList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val author: String,
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val title: String,
        val type: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LitterNewContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val author: String,
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val title: String,
        val type: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LitterNewsCommentList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val newsId: Int,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        class Params
    }
}
data class LitterNewsComment(
    val content: String,
    val newsId: Int
)

data class LitterTypeBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class LitterList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val type: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class LitterType(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val guide: String,
        val id: Int,
        val imgUrl: String,
        val introduce: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LitterHot(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val keyword: String,
        val params: Params,
        val remark: Any,
        val searchTimes: Int,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LawyerList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val avatarUrl: String,
        val baseInfo: String,
        val certificateImgUrl: String,
        val createBy: Any,
        val createTime: Any,
        val eduInfo: String,
        val favorableCount: Int,
        val favorableRate: Int,
        val id: Int,
        val legalExpertiseId: Int,
        val legalExpertiseName: String,
        val licenseNo: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val serviceTimes: Int,
        val sort: String,
        val updateBy: Any,
        val updateTime: Any,
        val workStartAt: String
    ) {
        class Params
    }
}

data class LawyerContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val avatarUrl: String,
        val baseInfo: String,
        val certificateImgUrl: String,
        val createBy: Any,
        val createTime: Any,
        val eduInfo: String,
        val favorableCount: Int,
        val favorableRate: Int,
        val id: Int,
        val legalExpertiseId: Int,
        val legalExpertiseName: String,
        val licenseNo: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val serviceTimes: Int,
        val sort: String,
        val updateBy: Any,
        val updateTime: Any,
        val workStartAt: String
    ) {
        class Params
    }
}

data class LawyerBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class LawyerExper(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class MyLawyerList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val content: String,
        val createBy: Any,
        val createTime: String,
        val evaluate: Any,
        val fromUserId: Int,
        val id: Int,
        val imageUrls: String,
        val lawyerId: Int,
        val lawyerName: String,
        val legalExpertiseId: Int,
        val legalExpertiseName: Any,
        val likeCount: Any,
        val params: Params,
        val phone: String,
        val remark: Any,
        val score: Int,
        val searchValue: Any,
        val state: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}


data class LawyerTen(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val avatarUrl: String,
        val baseInfo: String,
        val certificateImgUrl: String,
        val createBy: Any,
        val createTime: Any,
        val eduInfo: String,
        val favorableCount: Int,
        val favorableRate: Int,
        val id: Int,
        val legalExpertiseId: Int,
        val legalExpertiseName: String,
        val licenseNo: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val serviceTimes: Int,
        val sort: String,
        val updateBy: Any,
        val updateTime: Any,
        val workStartAt: String
    ) {
        class Params
    }
}

data class LawyerComment(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createTime: String,
        val evaluateContent: String,
        val fromUserAvatar: String,
        val fromUserName: String,
        val likeCount: Int,
        val myLikeState: Boolean,
        val score: Int
    )
}

data class ConsutleList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val content: String,
        val createBy: Any,
        val createTime: String,
        val evaluate: Any,
        val fromUserId: Int,
        val id: Int,
        val imageUrls: String,
        val lawyerId: Int,
        val lawyerName: String,
        val legalExpertiseId: Int,
        val legalExpertiseName: String,
        val likeCount: Any,
        val params: Params,
        val phone: String,
        val remark: Any,
        val score: Int,
        val searchValue: Any,
        val state: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class ConsutleListContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val content: String,
        val createBy: Any,
        val createTime: String,
        val evaluate: Any,
        val fromUserId: Int,
        val id: Int,
        val imageUrls: String,
        val lawyerId: Int,
        val lawyerName: String,
        val legalExpertiseId: Int,
        val legalExpertiseName: Any,
        val likeCount: Any,
        val params: Params,
        val phone: String,
        val remark: Any,
        val score: Int,
        val searchValue: Any,
        val state: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class Consutle(
    val content: String,
    val lawyerId: Int,
    val legalExpertiseId: Int,
    val phone: String
)

data class Com(
    val evaluate: String,
    val id: Int,
    val score: Int
)