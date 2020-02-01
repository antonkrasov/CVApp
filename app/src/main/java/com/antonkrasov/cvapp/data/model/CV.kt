package com.antonkrasov.cvapp.data.model

import com.google.gson.annotations.SerializedName

data class CV(
    @SerializedName("title") val title: String?,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("details") val details: Details?,
    @SerializedName("description") val description: String?,
    @SerializedName("links") val links: Array<String>?,
    @SerializedName("skills") val skills: Array<String>?,
    @SerializedName("sections") val sections: Array<Section>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CV

        if (title != other.title) return false
        if (subtitle != other.subtitle) return false
        if (details != other.details) return false
        if (description != other.description) return false
        if (links != null) {
            if (other.links == null) return false
            if (!links.contentEquals(other.links)) return false
        } else if (other.links != null) return false
        if (skills != null) {
            if (other.skills == null) return false
            if (!skills.contentEquals(other.skills)) return false
        } else if (other.skills != null) return false
        if (sections != null) {
            if (other.sections == null) return false
            if (!sections.contentEquals(other.sections)) return false
        } else if (other.sections != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (subtitle?.hashCode() ?: 0)
        result = 31 * result + (details?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (links?.contentHashCode() ?: 0)
        result = 31 * result + (skills?.contentHashCode() ?: 0)
        result = 31 * result + (sections?.contentHashCode() ?: 0)
        return result
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