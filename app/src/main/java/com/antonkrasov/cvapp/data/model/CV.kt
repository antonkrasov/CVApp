package com.antonkrasov.cvapp.data.model

import com.google.gson.annotations.SerializedName

data class CV(
    @SerializedName("title") val title: String?,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("details") val details: Details?,
    @SerializedName("description") val description: String?,
    @SerializedName("links") val links: Array<String>?,
    @SerializedName("skills") val skills: Array<String>?,
    @SerializedName("sections") val sections: Array<Section>?,
    @SerializedName("last_update") val lastUpdate: Long?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CV

        if (lastUpdate != other.lastUpdate) return false

        return true
    }

    override fun hashCode(): Int {
        return lastUpdate?.hashCode() ?: 0
    }

}

data class Details(
    @SerializedName("location") val location: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("email") val email: String?
) {
    fun buildSingleString(): String? {
        return "$location\n$phone\n$email"
    }
}

data class DataItem(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("start_date") val startDate: String?,
    @SerializedName("end_date") val endDate: String?
) {
    fun buildTime(): String? {
        return if (startDate == null || endDate == null) "" else "$startDate - $endDate"
    }
}

data class Section(
    @SerializedName("title") val title: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("items") val items: Array<DataItem>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Section

        if (title != other.title) return false
        if (icon != other.icon) return false
        if (items != null) {
            if (other.items == null) return false
            if (!items.contentEquals(other.items)) return false
        } else if (other.items != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (icon?.hashCode() ?: 0)
        result = 31 * result + (items?.contentHashCode() ?: 0)
        return result
    }
}