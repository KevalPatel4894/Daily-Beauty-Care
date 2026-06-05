package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object Straight {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.coconutmilk_lemonjuice)
        listModel.descriptionName = context.getString(R.string.thirty_min)
        listModel.image = R.drawable.eyelashes_coconutmilk
        listModel.details = context.getString(R.string.straight_coconutmilk_lemonjuice)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hotoiltreatment)
        listModel1.descriptionName = context.getString(R.string.thirty_min)
        listModel1.image = R.drawable.hotoil_split
        listModel1.details = context.getString(R.string.straight_hotoiltreatment)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.milkspray)
        listModel2.descriptionName = context.getString(R.string.thirty_min)
        listModel2.image = R.drawable.darkcrcl_milk
        listModel2.details = context.getString(R.string.straight_milkspray)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.egg_oliveoil)
        listModel3.descriptionName = context.getString(R.string.thirty_min)
        listModel3.image = R.drawable.lice_oliveoil
        listModel3.details = context.getString(R.string.straight_egg_oliveoil)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.milk_honey)
        listModel4.descriptionName = context.getString(R.string.two_hr)
        listModel4.image = R.drawable.milk_honey
        listModel4.details = context.getString(R.string.straight_milk_honey)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.riceflour_eggmask)
        listModel5.descriptionName = context.getString(R.string.one_hr)
        listModel5.image = R.drawable.heels_riceflour
        listModel5.details = context.getString(R.string.straight_riceflour_eggmask)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.aloe_vera_gel)
        listModel6.descriptionName = context.getString(R.string.forty_min)
        listModel6.image = R.drawable.darkcrcl_aloeveragel
        listModel6.details = context.getString(R.string.straight_aloevera)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.banana_curd_oliveoil)
        listModel7.descriptionName = context.getString(R.string.thirty_min)
        listModel7.image = R.drawable.banana_curd_oliveoil
        listModel7.details = context.getString(R.string.straight_banana_curd_oliveoil)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.apple_cider_vinegar)
        listModel8.descriptionName = context.getString(R.string.five_min)
        listModel8.image = R.drawable.apple_vinegar_dandruff
        listModel8.details = context.getString(R.string.straight_apple_cider_vinegor)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.banana_papaya)
        listModel9.descriptionName = context.getString(R.string.fortyfive_min)
        listModel9.image = R.drawable.wrinkle_papayabanana
        listModel9.details = context.getString(R.string.straight_banana_papaya)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.salmon)
        listModel.descriptionName = context.getString(R.string.healthy_fats)
        listModel.image = R.drawable.eyelashes_salmon
        listModel.details = context.getString(R.string.frizzy_salmon)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.citrus_fruits)
        listModel1.descriptionName = context.getString(R.string.oranges_lemon_lime_grapefruit)
        listModel1.image = R.drawable.warts_fruits
        listModel1.details = context.getString(R.string.frizzy_citrus_fruits)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.blueberries)
        listModel2.descriptionName = context.getString(R.string.antioxidants)
        listModel2.image = R.drawable.darkcrcl_blueberries
        listModel2.details = context.getString(R.string.frizzy_blueberries)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.walnut)
        listModel3.descriptionName = context.getString(R.string.omega3_biotin)
        listModel3.image = R.drawable.walnut_dandruff
        listModel3.details = context.getString(R.string.frizzy_walnut)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.sweet_potatoes)
        listModel4.descriptionName = context.getString(R.string.beta_carotene)
        listModel4.image = R.drawable.blemishes_sweetpotato
        listModel4.details = context.getString(R.string.frizzy_sweet_potatoes)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.yogurt)
        listModel5.descriptionName = context.getString(R.string.protein_vitamin_d)
        listModel5.image = R.drawable.dryhands_yogurt
        listModel5.details = context.getString(R.string.frizzy_yogurt)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.chicken)
        listModel6.descriptionName = context.getString(R.string.protein)
        listModel6.image = R.drawable.chicken
        listModel6.details = context.getString(R.string.frizzy_chicken)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.vitamins)
        listModel7.descriptionName = context.getString(R.string.vitamins_detail)
        listModel7.image = R.drawable.lice_vitamineral
        listModel7.details = context.getString(R.string.frizzy_vitamins)
        list.add(listModel7)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.keep_straight_during_workout)
        listModel.descriptionName = context.getString(R.string.during_workout)
        listModel.image = R.drawable.yoga_title
        listModel.details = context.getString(R.string.keep_straight_during_workout_detail)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.additional_tips)
        listModel1.descriptionName = context.getString(R.string.more_tips)
        listModel1.image = R.drawable.straighthair_title
        listModel1.details = context.getString(R.string.frizzy_what_to_avoid)
        list.add(listModel1)
        return list
    }
}