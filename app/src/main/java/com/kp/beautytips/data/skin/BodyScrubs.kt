package com.kp.beautytips.data.skin

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object BodyScrubs {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.coffee_scrub)
        listModel.descriptionName = context.getString(R.string.fifteen_min)
        listModel.image = R.drawable.coffee_sugar
        listModel.details = context.getString(R.string.body_scrub_coffee_sugar)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.sea_salt)
        listModel1.descriptionName = context.getString(R.string.fifteen_min)
        listModel1.image = R.drawable.sea_salt
        listModel1.details = context.getString(R.string.body_scrub_sea_salt)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.coconut_oil_sugar)
        listModel2.descriptionName = context.getString(R.string.fifteen_min)
        listModel2.image = R.drawable.coconutoil_hairfall_remedy
        listModel2.details = context.getString(R.string.body_scrub_coconut_oil_sugar)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.olive_oil_peppermint_sugar)
        listModel3.descriptionName = context.getString(R.string.fifteen_min)
        listModel3.image = R.drawable.oliveoil_peppermint_sugar
        listModel3.details = context.getString(R.string.body_scrub_olive_oil_peppermint_sugar)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.epsom_salt)
        listModel4.descriptionName = context.getString(R.string.fifteen_min)
        listModel4.image = R.drawable.blackheads_epsomsalt
        listModel4.details = context.getString(R.string.body_scrub_epsom_salt)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.oatmeal)
        listModel5.descriptionName = context.getString(R.string.fifteen_min)
        listModel5.image = R.drawable.dryhands_oatmeal
        listModel5.details = context.getString(R.string.body_scrub_oatmeal)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.yogurt)
        listModel6.descriptionName = context.getString(R.string.fifteen_min)
        listModel6.image = R.drawable.dryhands_yogurt
        listModel6.details = context.getString(R.string.body_scrub_yogurt)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.vanila_sugar)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.vanilla_sugar
        listModel7.details = context.getString(R.string.body_scrub_vanila_sugar)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.orangejuice_turmeric)
        listModel8.descriptionName = context.getString(R.string.fifteen_min)
        listModel8.image = R.drawable.thighs_turmeric
        listModel8.details = context.getString(R.string.body_scrub_organic_turmeric)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.lemon_sugar)
        listModel9.descriptionName = context.getString(R.string.fifteen_min)
        listModel9.image = R.drawable.lemon_sugar
        listModel9.details = context.getString(R.string.body_scrub_lemon_sugar)
        list.add(listModel9)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.skin_glowingskin_main_one)
        listModel.descriptionName = context.getString(R.string.skin_glowingskin_detail_one)
        listModel.image = R.drawable.hairfall_carrot_diet
        listModel.details = context.getString(R.string.glowingskin_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.skin_glowingskin_main_two)
        listModel1.descriptionName = context.getString(R.string.skin_glowingskin_detail_two)
        listModel1.image = R.drawable.glowing_karela
        listModel1.details = context.getString(R.string.glowingskin_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.skin_glowingskin_main_three)
        listModel2.descriptionName = context.getString(R.string.skin_glowingskin_detail_three)
        listModel2.image = R.drawable.darkcrcl_oranges
        listModel2.details = context.getString(R.string.glowingskin_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.skin_glowingskin_main_four)
        listModel3.descriptionName = context.getString(R.string.skin_glowingskin_detail_four)
        listModel3.image = R.drawable.eyelashes_salmon
        listModel3.details = context.getString(R.string.glowingskin_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.skin_glowingskin_main_five)
        listModel4.descriptionName = context.getString(R.string.skin_glowingskin_detail_five)
        listModel4.image = R.drawable.spinach_dandruff
        listModel4.details = context.getString(R.string.glowingskin_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.skin_glowingskin_main_six)
        listModel5.descriptionName = context.getString(R.string.skin_glowingskin_detail_six)
        listModel5.image = R.drawable.glowing_grapefruit
        listModel5.details = context.getString(R.string.glowingskin_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.skin_glowingskin_main_seven)
        listModel6.descriptionName = context.getString(R.string.skin_glowingskin_detail_seven)
        listModel6.image = R.drawable.darkcrcl_blueberries
        listModel6.details = context.getString(R.string.glowingskin_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.skin_glowingskin_main_eight)
        listModel7.descriptionName = context.getString(R.string.skin_glowingskin_detail_eight)
        listModel7.image = R.drawable.hairfall_oyster_diet
        listModel7.details = context.getString(R.string.glowingskin_diet_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.skin_glowingskin_main_nine)
        listModel8.descriptionName = context.getString(R.string.skin_glowingskin_detail_nine)
        listModel8.image = R.drawable.walnut_dandruff
        listModel8.details = context.getString(R.string.glowingskin_diet_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.skin_glowingskin_main_ten)
        listModel9.descriptionName = context.getString(R.string.skin_glowingskin_detail_ten)
        listModel9.image = R.drawable.tomato_dandruff
        listModel9.details = context.getString(R.string.glowingskin_diet_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.skin_glowingskin_main_eleven)
        listModel10.descriptionName = context.getString(R.string.skin_glowingskin_detail_eleven)
        listModel10.image = R.drawable.egg_dandruff
        listModel10.details = context.getString(R.string.glowingskin_diet_eleven)
        list.add(listModel10)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.scrub_general)
        listModel.descriptionName = context.getString(R.string.scrub_general_detail)
        listModel.image = R.drawable.bodyscrub_general
        listModel.details = context.getString(R.string.scrub_general_exercise)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.scrub_for_face)
        listModel1.descriptionName = context.getString(R.string.scrub_for_face_detail)
        listModel1.image = R.drawable.scrub_on_face
        listModel1.details = context.getString(R.string.scrub_for_face_exercise)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.scrub_for_lips)
        listModel2.descriptionName = context.getString(R.string.scrub_for_lips_detail)
        listModel2.image = R.drawable.darklips_title
        listModel2.details = context.getString(R.string.scrub_for_lips_exercise)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.scrub_for_back)
        listModel3.descriptionName = context.getString(R.string.scrub_for_back_detail)
        listModel3.image = R.drawable.polish_detoxing
        listModel3.details = context.getString(R.string.scrub_for_back_exercise)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.additional_tips)
        listModel4.descriptionName = context.getString(R.string.more_tips)
        listModel4.image = R.drawable.bodyscrub_title
        listModel4.details = context.getString(R.string.scrub_additional_exercise)
        list.add(listModel4)
        return list
    }
}