package dev.sterner.witchery.data_gen.book.entry

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase
import com.klikli_dev.modonomicon.api.datagen.EntryBackground
import com.klikli_dev.modonomicon.api.datagen.EntryProvider
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel
import com.klikli_dev.modonomicon.api.datagen.book.page.BookCraftingRecipePageModel
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel
import com.mojang.datafixers.util.Pair
import dev.sterner.witchery.Witchery
import dev.sterner.witchery.core.registry.WitcheryItems

class AltarEntryProvider(parent: CategoryProviderBase?) : EntryProvider(parent) {

    companion object {
        val ID = "altar"
    }

    override fun generatePages() {
        this.page(ID) {
            BookSpotlightPageModel.create()
                .withItem(WitcheryItems.DEEPSLATE_ALTAR_BLOCK.get())
                .withTitle("${parent.categoryId()}.$ID.title.1")
                .withText("${parent.categoryId()}.$ID.page.1")
        }
        this.page("${parent.categoryId()}.${ID}") {
            BookCraftingRecipePageModel.create().withText("${parent.categoryId()}.${ID}.title.2")
                .withRecipeId1(Witchery.id("deepslate_altar_block"))
                .withTitle1("${parent.categoryId()}.${ID}")
        }
        this.page("${parent.categoryId()}.${ID}.modifiers") {
            BookTextPageModel.create().withText("${parent.categoryId()}.${ID}.modifiers.1")
        }
    }

    override fun entryName(): String {
        return ID.replaceFirstChar { it.uppercaseChar() }
    }

    override fun entryDescription(): String {
        return ""
    }

    override fun entryBackground(): Pair<Int, Int> {
        return EntryBackground.DEFAULT
    }

    override fun entryIcon(): BookIconModel {
        return BookIconModel.create(WitcheryItems.DEEPSLATE_ALTAR_BLOCK.get())
    }

    override fun entryId(): String {
        return ID
    }
}