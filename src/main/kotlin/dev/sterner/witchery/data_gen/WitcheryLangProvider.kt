package dev.sterner.witchery.data_gen

import dev.sterner.witchery.core.api.SpecialPotion
import dev.sterner.witchery.data_gen.lang.WitcheryAdvancementLangProvider
import dev.sterner.witchery.data_gen.lang.WitcheryBookLangProvider
import dev.sterner.witchery.data_gen.lang.WitcheryRitualLangProvider
import dev.sterner.witchery.core.registry.*
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.common.data.LanguageProvider

class WitcheryLangProvider(output: PackOutput, modid: String, locale: String) :
    LanguageProvider(output, modid, locale) {

    private fun formatId(id: ResourceLocation): String {
        val name = id.path.split('.').last()
        return formatId(name)
    }

    private fun formatId(name: String): String {
        val exceptions = setOf("of", "the", "and", "in", "for", "on", "to")

        return name
            .removeSuffix("_component")
            .split('_')
            .joinToString(" ") { word ->
                if (word in exceptions) {
                    word.lowercase()
                } else {
                    word.replaceFirstChar { it.uppercase() }
                }
            }
    }

    override fun addTranslations() {
        add("itemGroup.witchery.main", "Witchery")
        add("curios.identifier.poppet", "Poppet")

        WitcheryRitualLangProvider.ritual(::add)
        WitcheryAdvancementLangProvider.advancement(::add)
        WitcheryBookLangProvider.book(::add)

        for (item in WitcheryItems.LANG_HELPER) {
            if (item == "apple_of_sleeping") {
                add("item.witchery.apple_of_sleeping", "Apple")
            } else if (item == "hags_ring") {
                add("item.witchery.hags_ring", "Hag's Ring")
            } else if (item == "censer_long") {
                add("item.witchery.censer_long", "Censer")
            } else if (item == "mutating_spring") {
                add("item.witchery.mutating_spring", "Mutating Sprig")
            } else {
                add("item.witchery.$item", formatId(item))
            }

        }

        for (block in WitcheryBlocks.LANG_HELPER) {
            add("block.witchery.$block", formatId(block))
        }

        for (entity in WitcheryEntityTypes.LANG_HELPER) {
            add("entity.witchery.$entity", formatId(entity))
        }

        for (entry in WitcherySpecialPotionEffects.SPECIAL_REGISTRY.entrySet()) {
            val key: ResourceKey<SpecialPotion>? = entry.key
            val id = key?.location()
            id?.let { add("witchery:${it.path}", formatId(id)) }
        }

        add("witchery.ritual.insufficient_witch_power", "Insufficient Witch Power, expand coven or get a Cat")
        add("tooltip.witchery.vampiric_poppet.owner", "Source:")
        add("tooltip.witchery.vampiric_poppet.target", "Sacrifice:")
        add("container.witchery.soul_trade_menu", "Soul Trade")
        add("entity.minecraft.villager.witchery.fortune_teller", "Fortune Teller")
        add("minecraft:the_end", "The End")
        add("item.witchery.leonards_urn.potions", "%s/%s Potions")
        add("item.witchery.quartz_sphere.loaded", "Loaded:")
        add("item.witchery.quartz_sphere.empty", "Empty - Use with Leonard's Urn")
        add("gui.witchery.select_potion", "Select Potion")
        add("gui.witchery.scroll_to_select", "Scroll to Select")

        add("item.witchery.tarot_deck.desc", "Fortune of three cards last 3 days")

        add("item.witchery.lifeblood_berry.tooltip", "Fills the soul with ethereal vitality")
        add("item.witchery.lifeblood_berry.tooltip2", "+5 Lifeblood")

        add("witchery.ability.death_teleport.already_used", "Already used this life")

        add("witchery.coven.needs_demon_heart", "This witch needs a Demon Heart to join your coven")
        add("witchery.coven.witch_limit", "Your coven has reached the maximum number of witches")
        add("witchery.coven.witch_added", "Witch has been bound to your coven")
        add("witchery.coven.witch_already_bound", "This witch is already bound to a coven")
        add("witchery.coven.player_limit", "Your coven has reached the maximum number of players")
        add("witchery.coven.already_member", "This player is already a member of your coven")
        add("witchery.coven.added_player", "%s has been added to your coven")
        add("witchery.coven.joined", "You have joined %s's coven")
        add("witchery.coven.not_member", "This player is not a member of your coven")
        add("witchery.coven.contract_signed", "You have signed the Coven Contract")
        add("witchery.coven.bound_members", "Successfully bound %s members to your coven")
        add("witchery.coven.summoned", "Summoned %s witches to the ritual circle")
        add("witchery.coven.no_witches", "You have no witches in your coven to summon")
        add("witchery.coven.no_ritual", "No ritual circle found nearby")
        add("witchery.coven.interrupted", "Ritual interrupted")
        add("witchery.coven.witch_died", "One of your coven witches has died")
        add("witchery.coven.disbanded", "You have been removed from the coven")
        add("witchery.coven.contract_destroyed", "The coven contract has been destroyed! Your coven has been disbanded.")

        add("witchery.ritual.curses_disabled", "This ritual cannot be performed - curses are disabled")
        add(WitcheryTags.ROWAN_LOG_ITEMS, "Rowan Logs")
        add(WitcheryTags.ALDER_LOG_ITEMS, "Alder Logs")
        add(WitcheryTags.HAWTHORN_LOG_ITEMS, "Hawthorn Logs")
        add(WitcheryTags.LEAF_ITEMS, "Witchery Leaves")
        add(WitcheryTags.CANDELABRA_ITEMS, "Candelabras")
        add(WitcheryTags.PLACEABLE_POPPETS, "Placeable Poppets")
        add(WitcheryTags.FROM_SPIRIT_WORLD_TRANSFERABLE, "From Spirit World Transferable")
        add(WitcheryTags.TO_SPIRIT_WORLD_TRANSFERABLE, "To Spirit World Transferable")

        add("death.attack.inSun", "Turned to ash but the sun")

        add("witchery.add_page.1", "Added the first page to the key")
        add("witchery.add_page.2", "Added the second page to the key")
        add("witchery.add_page.3", "Added the third page to the key")
        add("witchery.add_page.4", "Added the forth page to the key")
        add("witchery.add_page.5", "Added the fifth page to the key")
        add("witchery.add_page.6", "Added the sixth page to the key")
        add("witchery.add_page.7", "Added the seventh page to the key")
        add("witchery.add_page.8", "Added the eight page to the key")
        add("witchery.add_page.9", "Added the final page to the key")

        add("emi.category.witchery.cauldron_brewing", "Cauldron Brewing")
        add("emi.category.witchery.cauldron_crafting", "Cauldron Crafting")
        add("emi.category.witchery.ritual", "Ritual")
        add("emi.category.witchery.oven_cooking", "Oven Fumigation")
        add("emi.category.witchery.distilling", "Distilling")
        add("emi.category.witchery.spinning", "Spinning")



        add("container.witchery.oven_menu", "Witches Oven")
        add("container.witchery.altar_menu", "Altar")
        add("container.witchery.spinning_wheel", "Spinning Wheel")
        add("container.witchery.distillery", "Distillery")

        add("trinkets.slot.chest.charm", "Charm")
        add("trinkets.slot.legs.poppet", "Poppet")

        add("witchery.secondbrewbonus.25", "+25% chance of second brew")
        add("witchery.secondbrewbonus.35", "+35% chance of second brew")
        add("witchery.thirdbrewbonus.25", "+25% chance of third brew")
        add("witchery.infusion.ointment", "Flying Ointment")

        add("witchery.blood", "Blood")
        add("witchery.vampire_blood", "Blood?")
        add("witchery.use_with_needle", "Use with Bone Needle to fill")

        add("witchery:all_worlds", "All Worlds")
        add("witchery:dream_world", "Dream World")
        add("witchery:nightmare_world", "Nightmare World")

        add("witchery.item.tooltip.infinity_egg", "Creative Only")

        add("witchery.celestial.day", "Day")
        add("witchery.celestial.full", "Full Moon")
        add("witchery.celestial.new", "New Moon")
        add("witchery.celestial.waning", "Waning Moon")
        add("witchery.celestial.waxing", "Waxing Moon")

        add("witchery.captured.silverfish", "Silverfish")
        add("witchery.captured.slime", "Slime")
        add("witchery.captured.bat", "Bat")

        add("witchery.attuned.charged", "Attuned")
        add("witchery.has_sun", "Sunlight")

        add("attribute.name.witchery.vampire_bat_form_duration", "Bat-form Duration")
        add("attribute.name.witchery.vampire_drink_speed", "Blooding Drink Speed")
        add("attribute.name.witchery.vampire_sun_resistance", "Sun Resistance")

        add("entity.witchery.rowan_boat", "Rowan Boat")
        add("entity.witchery.rowan_chest_boat", "Rowan Chest Boat")
        add("entity.witchery.alder_boat", "Alder Boat")
        add("entity.witchery.alder_chest_boat", "Alder Chest Boat")
        add("entity.witchery.hawthorn_boat", "Hawthorn Boat")
        add("entity.witchery.hawthorn_chest_boat", "Hawthorn Chest Boat")

        add("witchery.brazier.category", "Brazier")
        add("witchery.cauldron_brewing.category", "Cauldron Brewing")
        add("witchery.cauldron_crafting.category", "Cauldron Crafting")
        add("witchery.ritual.category", "Ritual")
        add("witchery.oven.category", "Oven Fumigation")
        add("witchery.distilling.category", "Distilling")
        add("witchery.spinning.category", "Spinning")

        add("emi.category.witchery.brazier", "Brazier")
        add("witchery:brazier_summoning/summon_banshee", "Summon Banshee")
        add("witchery:brazier_summoning/summon_banshee.tooltip", "Summons a Banshee")
        add("witchery:brazier_summoning/summon_spectre", "Summon Spectre")
        add("witchery:brazier_summoning/summon_spectre.tooltip", "Summons a Spectre")
        add("witchery:brazier_summoning/summon_poltergeist", "Summon Poltergeist")
        add("witchery.brazier_summoning/summon_poltergeist.tooltip", "Summons a Poltergeist")
        add("witchery:brazier_summoning/summon_poltergeist.tooltip", "Summons a Poltergeist")

        add("witchery.too_few_in_coven", "Coven too small")

        add("witchery.curse.afflicted", "%s is afflicted by %s")
        add("witchery.curse.free", "%s is free from curses!")

        add("witchery.curse.misfortune.name", "the Curse of Misfortune")
        add("witchery.curse.insanity.name", "the Curse of Insanity")
        add("witchery.curse.corrupt_poppet.name", "the Curse of Corrupt Poppet")
        add("witchery.curse.overheating.name", "the Curse of Overheating")
        add("witchery.curse.sinking.name", "the Curse of Sinking")
        add("witchery.curse.befuddlement.name", "the Curse of Befuddlement")
        add("witchery.curse.hunger.name", "the Curse of Hunger")
        add("witchery.curse.fragility.name", "the Curse of Fragility")

        add("key.categories.witchery", "Witchery")
        add("key.witchery.dismount", "Dismount Broom")
        add("key.witchery.edit_hud", "Edit HUD")
        add("key.witchery.open_ability_selection", "Open Ability Selection")
        add("key.witchery.utility_button", "Utility Button")
        add("key.witchery.toggle_quest_hud", "Quest HUD")

        add("tarot.witchery.the_fool", "The Fool")
        add("tarot.witchery.the_fool.reversed", "The Fool (Reversed)")
        add("tarot.witchery.the_magician", "The Magician")
        add("tarot.witchery.the_magician.reversed", "The Magician (Reversed)")
        add("tarot.witchery.the_high_priestess", "The High Priestess")
        add("tarot.witchery.the_high_priestess.reversed", "The High Priestess (Reversed)")
        add("tarot.witchery.the_empress", "The Empress")
        add("tarot.witchery.the_empress.reversed", "The Empress (Reversed)")
        add("tarot.witchery.the_emperor", "The Emperor")
        add("tarot.witchery.the_emperor.reversed", "The Emperor (Reversed)")
        add("tarot.witchery.the_hierophant", "The Hierophant")
        add("tarot.witchery.the_hierophant.reversed", "The Hierophant (Reversed)")
        add("tarot.witchery.the_lovers", "The Lovers")
        add("tarot.witchery.the_lovers.reversed", "The Lovers (Reversed)")
        add("tarot.witchery.the_chariot", "The Chariot")
        add("tarot.witchery.the_chariot.reversed", "The Chariot (Reversed)")
        add("tarot.witchery.strength", "Strength")
        add("tarot.witchery.strength.reversed", "Strength (Reversed)")
        add("tarot.witchery.the_hermit", "The Hermit")
        add("tarot.witchery.the_hermit.reversed", "The Hermit (Reversed)")
        add("tarot.witchery.wheel_of_fortune", "Wheel of Fortune")
        add("tarot.witchery.wheel_of_fortune.reversed", "Wheel of Fortune (Reversed)")
        add("tarot.witchery.justice", "Justice")
        add("tarot.witchery.justice.reversed", "Justice (Reversed)")
        add("tarot.witchery.the_hanged_man", "The Hanged Man")
        add("tarot.witchery.the_hanged_man.reversed", "The Hanged Man (Reversed)")
        add("tarot.witchery.death", "Death")
        add("tarot.witchery.death.reversed", "Death (Reversed)")
        add("tarot.witchery.temperance", "Temperance")
        add("tarot.witchery.temperance.reversed", "Temperance (Reversed)")
        add("tarot.witchery.the_devil", "The Devil")
        add("tarot.witchery.the_devil.reversed", "The Devil (Reversed)")
        add("tarot.witchery.the_tower", "The Tower")
        add("tarot.witchery.the_tower.reversed", "The Tower (Reversed)")
        add("tarot.witchery.the_star", "The Star")
        add("tarot.witchery.the_star.reversed", "The Star (Reversed)")
        add("tarot.witchery.the_moon", "The Moon")
        add("tarot.witchery.the_moon.reversed", "The Moon (Reversed)")
        add("tarot.witchery.the_sun", "The Sun")
        add("tarot.witchery.the_sun.reversed", "The Sun (Reversed)")
        add("tarot.witchery.judgement", "Judgement")
        add("tarot.witchery.judgement.reversed", "Judgement (Reversed)")
        add("tarot.witchery.the_world", "The World")
        add("tarot.witchery.the_world.reversed", "The World (Reversed)")

        // Tarot Card Descriptions
        add("tarot.witchery.the_fool.description", "Naive luck protects you - reduced damage taken, random beneficial effects")
        add("tarot.witchery.the_fool.reversed.description", "Clumsy mishaps and increased damage plague your journey")
        add("tarot.witchery.the_magician.description", "Master of the craft - altars recharge each morning, brews may return to you")
        add("tarot.witchery.the_magician.reversed.description", "Magic backfires - nearby altars drain power each dawn")
        add("tarot.witchery.the_high_priestess.description", "Perpetual night vision reveals hidden ores when mining - secrets glow briefly")
        add("tarot.witchery.the_high_priestess.reversed.description", "Intuition blocked - lose experience when mining")
        add("tarot.witchery.the_empress.description", "Nature's bounty - bonus crop drops, awaken each morning well-fed")
        add("tarot.witchery.the_empress.reversed.description", "Barren harvest - crops may fail when broken")
        add("tarot.witchery.the_emperor.description", "Command the battlefield - gain damage resistance at dawn, your strikes slow enemies")
        add("tarot.witchery.the_emperor.reversed.description", "Your authority crumbles - lose experience each dawn")
        add("tarot.witchery.the_hierophant.description", "Blessed each dawn with absorption, sleeping fully restores your health")
        add("tarot.witchery.the_hierophant.reversed.description", "Divine grace withheld - take damage each morning")
        add("tarot.witchery.the_lovers.description", "Animals are calmed by your presence, panic fades in your aura")
        add("tarot.witchery.the_lovers.reversed.description", "Love twisted - peaceful creatures turn violent against you and each other")
        add("tarot.witchery.the_chariot.description", "Accelerated movement - you move with enhanced speed and agility")
        add("tarot.witchery.the_chariot.reversed.description", "Your movement is hindered, as if pulling a great weight")
        add("tarot.witchery.strength.description", "Enhanced might flows through you - strike harder and heal from each kill")
        add("tarot.witchery.strength.reversed.description", "Your muscles betray you, constant weakness afflicts your blows")
        add("tarot.witchery.the_hermit.description", "Solitude breeds wisdom - gain experience when far from others")
        add("tarot.witchery.the_hermit.reversed.description", "Isolation saps your vitality - lose max health when alone")
        add("tarot.witchery.wheel_of_fortune.description", "Fortune's favor - increased luck, rare drops from slain enemies")
        add("tarot.witchery.wheel_of_fortune.reversed.description", "The wheel turns against you - constant bad luck")
        add("tarot.witchery.justice.description", "An eye for an eye - those who harm you suffer thorns damage in return")
        add("tarot.witchery.justice.reversed.description", "Unfair punishment - you take more damage from all sources")
        add("tarot.witchery.the_hanged_man.description", "Suffering empowers nearby altars - falling is slower, pain fuels magic")
        add("tarot.witchery.the_hanged_man.reversed.description", "Unable to release items from your grasp")
        add("tarot.witchery.death.description", "Endings bring new beginnings - fallen foes may rise as ethereal servants, ailments fade at dawn")
        add("tarot.witchery.death.reversed.description", "Decay drains your vitality, and Death itself stalks you at dusk")
        add("tarot.witchery.temperance.description", "Harmony restored - slow regeneration when wounded, water breathing, enhanced potions")
        add("tarot.witchery.temperance.reversed.description", "Excess and imbalance plague your actions")
        add("tarot.witchery.the_devil.description", "Devastating strength at the cost of your vitality - reduced max health for increased damage")
        add("tarot.witchery.the_devil.reversed.description", "Slowly break free from curses and debuffs, but lose experience in the process")
        add("tarot.witchery.the_tower.description", "Chaos incarnate - blocks may explode when broken, Baba Yaga may appear")
        add("tarot.witchery.the_tower.reversed.description", "Stagnation without growth or change")
        add("tarot.witchery.the_star.description", "Slow regeneration under stars, fully restored when night falls")
        add("tarot.witchery.the_star.reversed.description", "Your guiding light fades - constant hunger drains your energy")
        add("tarot.witchery.the_moon.description", "Night vision, speed at dusk, enemies lose track of you in darkness")
        add("tarot.witchery.the_moon.reversed.description", "Harsh daylight occasionally blinds you - clarity obscures truth")
        add("tarot.witchery.the_sun.description", "Full restoration each dawn, slow healing in daylight, strength and regeneration")
        add("tarot.witchery.the_sun.reversed.description", "Scorching rays - daylight burns you beneath open sky")
        add("tarot.witchery.judgement.description", "A second chance when death looms - rise reborn from mortal wounds, heal from victory")
        add("tarot.witchery.judgement.reversed.description", "Each kill weighs on your soul, damaging you in turn")
        add("tarot.witchery.the_world.description", "Perfect completion - speed, haste, luck, regeneration, bonus drops, experience at dawn")
        add("tarot.witchery.the_world.reversed.description", "Discord and incompletion - random debuffs plague you")

        add("emi.category.witchery.cauldron_infusion", "Cauldron Infusion")
        add("witchery.cauldron_infusion.category", "Cauldron Infusion")

        add("witchery.hag_type.miner", "Miner's Infusion")
        add("witchery.hag_type.lumber", "Lumber's Infusion")
        add("witchery.hag_type.reach", "Reacher's Infusion")
        add("witchery.hag_ring.fortune", "Fortune %s")

        add("witchery.hags_ring.when_worn", "When worn as ring:")
        add("witchery.hags_ring.miner.desc", "Use with Witches Hand to vein mine ores")
        add("witchery.hags_ring.lumber.desc", "Use with Witches Hand to vein mine logs")

        add("effect.witchery.bear_trap_incapacitated", "Bear Trap Incapacitated")

        add("curse.witchery.corrupt_poppet.corrupted", "Corrupted a Poppet")

        add("witchery.chalice.filled", "Filled")
        add("witchery.book.grant_all", "Grant all Witchery advancements")

        add("witchery.quest.lycanthropy", "Lycanthropy")
        add("witchery.quest.vampirism", "Vampirism")
        add("witchery.quest.lichdom", "Lichdom")
        add("witchery.tarot.already", "Your fortune this week has already been decided.")
        add("witchery.tarot.dont_know", "You don't know how to use this.")
        add("witchery.tarot.draw_3", "Draw 3 cards from the Major Arcana")
        add("witchery.ability.select", "Select Abilities")
        add("witchery.ability.select_5", "Select 5 Active Abilities")
        add("witchery.ability.selected", "Selected: ")
        add("witchery.ability.active_abilities", "Active Abilities")
        add("witchery.ability.selected_2", "Selected")
        add("witchery.ability.max_selected", "Max abilities selected")
        add("witchery.ability.click_select", "Click to select")

        add("witchery.tarot_reading", "Tarot Reading")
        add("witchery.tarot_reading.draw", "Draw to seal your fate")
        add("witchery.tarot_card.reversed", "Reversed Card")
        add("witchery.tarot_card.negative", "Negative Effect")
        add("witchery.tarot_card.upright", "Upright Card")
        add("witchery.tarot_card.positive", "Positive Effect")
        add("witchery.tarot_reading.effect", "Effect:")

        add("witchery.quest.werewolf.give_gold", "Give 3 gold ingots to altar")
        add("witchery.quest.werewolf.kill_sheep", "Kill sheep")
        add("witchery.quest.werewolf.mutton", "Offer 30 mutton to altar")
        add("witchery.quest.werewolf.wolves", "Kill wolves")
        add("witchery.quest.werewolf.tongues", "Offer 10 tongues to altar")
        add("witchery.quest.werewolf.huntsman", "Kill Horned Huntsman")
        add("witchery.quest.werewolf.air", "Kill monsters in air")
        add("witchery.quest.werewolf.howl", "Howl at night in different areas")
        add("witchery.quest.werewolf.pack", "Form wolf pack")
        add("witchery.quest.werewolf.piglins", "Kill piglins")
        add("witchery.quest.werewolf.player", "Kill a Player or Villager")

        add("witchery.quest.vampire.fill_blood", "Fill your blood pool")
        add("witchery.quest.vampire.read", "Read Torn Page to reveal quest")
        add("witchery.quest.torn_page", "Read a Torn Page #%s")

        add("witchery.quest.vampire.half_blood", "Suck half-blood of villagers")
        add("witchery.quest.vampire.night", "Survive nights")
        add("witchery.quest.vampire.grenades", "Use sun grenades")
        add("witchery.quest.vampire.blazes", "Kill blazes")
        add("witchery.quest.vampire.poppy", "Give a Poppy to Lilith")
        add("witchery.quest.vampire.visit", "Visit villages as bat")
        add("witchery.quest.vampire.trap", "Trap villagers")


        add("witchery.quest.lich.tablet", "Read Ancient Tablet")
        add("witchery.quest.lich.tablet_reveal", "Read Ancient Tablet to reveal quest")
        add("witchery.quest.lich.bind_souls", "Bind souls")
        add("witchery.quest.lich.zombie_kill", "Zombie slave kills mob")
        add("witchery.quest.lich.golems", "Kill golems")
        add("witchery.quest.lich.drain", "Drain animals")
        add("witchery.quest.lich.possess", "Possess and kill villager")
        add("witchery.quest.lich.wither", "Kill wither")
        add("witchery.quest.lich.die", "Die with phylactery")

        add("witchery.command.yes", "Yes")
        add("witchery.command.no", "No")
        add("witchery.command.online", "Online")
        add("witchery.command.offline", "Offline")
        add("witchery.command.active", "Active")
        add("witchery.command.dead", "Dead")

        add("witchery.command.error.not_villager", "Target must be a villager")
        add("witchery.command.error.not_living", "Target must be a living entity")

        add("witchery.command.villagerwerewolf.infected", "Infected villager %s. Transformation in 20 seconds.")
        add("witchery.command.villagerwerewolf.cured", "Cured villager %s of lycanthropy.")
        add("witchery.command.villagerwerewolf.infect_all", "Infected %s villagers within %s blocks.")
        add("witchery.command.villagerwerewolf.status.header", "=== Werewolf Status for %s ===")
        add("witchery.command.villagerwerewolf.status.infected", "Infected: %s")
        add("witchery.command.villagerwerewolf.status.progress", "Infection Progress: %s/400 ticks")
        add("witchery.command.villagerwerewolf.status.is_werewolf", "Is Werewolf: %s")
        add("witchery.command.villagerwerewolf.status.should_transform", "Should Transform Now: %s")
        add("witchery.command.villagerwerewolf.status.moon_phase", "Moon Phase: %s")
        add("witchery.command.villagerwerewolf.status.is_day", "Is Day: %s")

        add("witchery.command.debug.hunger.set", "Set %s's hunger to %s")

        add("witchery.command.infusion.get", "Current infusion type: %s for player %s")

        add("witchery.command.manifestation.get", "Current manifestation status: %s for player %s")

        add("witchery.command.curse.none", "No curses on %s")
        add("witchery.command.curse.list", "Curses on %s: %s")

        add("witchery.command.tarot.invalid_card", "Invalid card number: %s. Must be between 1 and 22.")
        add("witchery.command.tarot.apply", "Applied tarot card %s to %s")
        add("witchery.command.tarot.granted", "You have been granted: %s")
        add("witchery.command.tarot.cleared", "Cleared all tarot cards from %s")
        add("witchery.command.tarot.cleared_self", "Your tarot cards have been cleared.")
        add("witchery.command.tarot.list.header", "=== Available Tarot Cards ===")
        add("witchery.command.tarot.list.entry", "%s. %s")
        add("witchery.command.tarot.info.no_cards", "%s has no active tarot cards")
        add("witchery.command.tarot.info.header", "=== Tarot Cards for %s ===")
        add("witchery.command.tarot.info.time_remaining", "Time Remaining: %sd %sh")
        add("witchery.command.tarot.info.description", "   %s")

        add("witchery.command.petrification.apply", "Petrified %s for %sm %ss")
        add("witchery.command.petrification.remove", "Removed petrification from %s")
        add("witchery.command.petrification.not_petrified", "%s is not petrified")
        add("witchery.command.petrification.status", "%s is petrified with %sm %ss remaining")

        add("witchery.command.coven.info.header", "=== Coven Info for %s ===")
        add("witchery.command.coven.info.witches", "Coven Witches: %s/%s active")
        add("witchery.command.coven.info.members_header", "Player Members (%s):")
        add("witchery.command.coven.info.member", "  - %s")
        add("witchery.command.coven.info.no_player_members", "No player members")
        add("witchery.command.coven.witches.header", "=== Coven Witches for %s ===")
        add("witchery.command.coven.witches.none", "%s has no coven witches")
        add("witchery.command.coven.witches.entry", "[%s] %s - %s (%s HP)")
        add("witchery.command.coven.members.header", "=== Player Members for %s ===")
        add("witchery.command.coven.members.none", "%s has no player members in their coven")
        add("witchery.command.coven.members.entry", "  - %s (%s)")
        add("witchery.command.coven.add_player.success", "Added %s to %s's coven")
        add("witchery.command.coven.add_player.failure", "Failed to add %s to coven")
        add("witchery.command.coven.remove_player.success", "Removed %s from %s's coven")
        add("witchery.command.coven.remove_player.failure", "Failed to remove %s from coven")
        add("witchery.command.coven.resurrect.success", "Resurrected witch at index %s for %s")
        add(
            "witchery.command.coven.resurrect.failure",
            "Failed to resurrect witch (invalid index or witch is already alive)"
        )
        add("witchery.command.coven.clear", "Cleared all coven members for %s")

        add("witchery.command.vampire.level.get", "Level: %s for %s")
        add("witchery.command.vampire.level.set", "Set vampire level to %s for %s")
        add("witchery.command.vampire.blood.set", "Set blood level to %s for %s")
        add("witchery.command.vampire.blood.get", "Blood Level: %s/%s")

        add("witchery.command.lichdom.level.get", "Level: %s for %s")
        add("witchery.command.lichdom.level.set", "Set lichdom level to %s for %s")
        add("witchery.command.lichdom.soul.set", "Set soul level to %s for %s")
        add("witchery.command.lichdom.soul.get", "Soul Level: %s/%s")

        add("witchery.command.werewolf.level.get", "Level %s for %s")
        add("witchery.command.werewolf.level.set", "Set werewolf level to %s for %s")

        add("witchery.hud_editor", "HUD Editor")
        add("witchery.hud_editor.manifestation", "Manifestation Meter")
        add("witchery.hud_editor.infusion", "Infusion Meter")
        add("witchery.hud_editor.bark_bet", "Bark Belt")
        add("witchery.hud_editor.quests", "Quests")
        add("witchery.hud_editor.reset", "Reset Positions")
        add("witchery.hud_editor.click_and_drag", "Click and drag HUD elements to reposition them")
        add("witchery.phylactery.bound", "Soul bound to phylactery.")
        add("witchery.phylactery.cannot", "You cannot bind more phylacteries.")

        add("witchery.ability.confirm", "Confirm")
        add("witchery.ability.bat_form", "Bat Form")
        add("witchery.ability.bite", "Bite")
        add("witchery.ability.death_teleport", "Death Teleport")
        add("witchery.ability.drink_blood", "Drink Blood")
        add("witchery.ability.night_howl", "Night Howl")
        add("witchery.ability.night_vision", "Night Vision")
        add("witchery.ability.pack_summon", "Pack Summon")
        add("witchery.ability.speed", "Speed")
        add("witchery.ability.transfix", "Transfix")
        add("witchery.ability.werewolf_form", "Werewolf Form")
        add("witchery.ability.wolf_form", "Wolf Form")
        add("witchery.ability.corpse_explosion", "Corpse Explosion")
        add("witchery.ability.life_drain", "Life Drain")
        add("witchery.ability.soul_form", "Soul Form")
        add("witchery.ability.summon_undead", "Summon Undead")

    }
}