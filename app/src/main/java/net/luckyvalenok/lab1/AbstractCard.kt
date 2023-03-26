package net.luckyvalenok.lab1

import android.os.Bundle
import androidx.core.os.bundleOf

abstract class AbstractCard(
    open val header: String = "Test subhead",
    open val subhead: String = "Test subhead",
    open val title: String = "Test title",
    open val titleSubhead: String = "Test subhead",
    open val description: String = "Test description"
) {
    val id: Int = genId++

    abstract val viewType: Int

    companion object {
        var genId = 1
    }

    fun toBundle(): Bundle = bundleOf(
        "id" to id,
        "header" to header,
        "subhead" to subhead,
        "title" to title,
        "titleSubhead" to titleSubhead,
        "description" to description
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractCard

        if (header != other.header) return false
        if (subhead != other.subhead) return false
        if (title != other.title) return false
        if (titleSubhead != other.titleSubhead) return false
        if (description != other.description) return false
        if (id != other.id) return false
        if (viewType != other.viewType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = header.hashCode()
        result = 31 * result + subhead.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + titleSubhead.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + id
        result = 31 * result + viewType
        return result
    }
}

class SmallCard(
    override val header: String,
    override val subhead: String,
    override val title: String,
    override val titleSubhead: String,
    override val description: String
) : AbstractCard() {
    override val viewType: Int = 0
}

class BigCard(
    override val header: String,
    override val subhead: String,
    override val title: String,
    override val titleSubhead: String,
    override val description: String
) : AbstractCard() {
    override val viewType: Int = 1
}