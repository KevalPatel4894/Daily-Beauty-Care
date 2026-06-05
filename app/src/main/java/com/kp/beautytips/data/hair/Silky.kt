package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object Silky {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.mayonnaise_mask)
        listModel.descriptionName = context.getString(R.string.twenty_min)
        listModel.image = R.drawable.mayonnaise_split
        listModel.details = context.getString(R.string.silky_bouncy_mayonnaise)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.banana_hairpack)
        listModel1.descriptionName = context.getString(R.string.twenty_min)
        listModel1.image = R.drawable.banana_split
        listModel1.details = context.getString(R.string.silky_bouncy_banana_hairpack)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.egg_hairmask)
        listModel2.descriptionName = context.getString(R.string.twenty_min)
        listModel2.image = R.drawable.eggmask_split
        listModel2.details = context.getString(R.string.silky_bouncy_egg_hairmask)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.protein_pack)
        listModel3.descriptionName = context.getString(R.string.thirty_min)
        listModel3.image = R.drawable.egg_yogurt
        listModel3.details = context.getString(R.string.silky_bouncy_protein_pack)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.onion_juice)
        listModel4.descriptionName = context.getString(R.string.thirty_min)
        listModel4.image = R.drawable.grey_onion_remedy
        listModel4.details = context.getString(R.string.silky_bouncy_onion_juice)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.fenugreek_paste)
        listModel5.descriptionName = context.getString(R.string.till_it_dry)
        listModel5.image = R.drawable.fenugreekseeds
        listModel5.details = context.getString(R.string.silky_bouncy_fenugreek_paste)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.yogurt)
        listModel6.descriptionName = context.getString(R.string.thirty_min)
        listModel6.image = R.drawable.dryhands_yogurt
        listModel6.details = context.getString(R.string.silky_bouncy_yogurt)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.potato_juice)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.darkcrcl_potato
        listModel7.details = context.getString(R.string.silky_bouncy_potato_juice)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.aloevera_honey)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.aloevera_split
        listModel8.details = context.getString(R.string.silky_bouncy_aloevera_honey)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.coconut_oil)
        listModel9.descriptionName = context.getString(R.string.thirty_min)
        listModel9.image = R.drawable.coconutoil_hairfall_remedy
        listModel9.details = context.getString(R.string.silky_bouncy_coconutoil)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.baking_soda)
        listModel10.descriptionName = context.getString(R.string.ten_min)
        listModel10.image = R.drawable.baking_soda_dandruff
        listModel10.details = context.getString(R.string.silky_bouncy_bakingsoda)
        list.add(listModel10)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.hair_drydmghair_main_one)
        listModel.descriptionName = context.getString(R.string.hair_drydmghair_detail_one)
        listModel.image = R.drawable.lice_protein
        listModel.details = context.getString(R.string.drydmghair_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hair_drydmghair_main_two)
        listModel1.descriptionName = context.getString(R.string.hair_drydmghair_detail_two)
        listModel1.image = R.drawable.drydmg_vitamina
        listModel1.details = context.getString(R.string.drydmghair_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.hair_drydmghair_main_three)
        listModel2.descriptionName = context.getString(R.string.hair_drydmghair_detail_three)
        listModel2.image = R.drawable.drydmg_vitaminb5
        listModel2.details = context.getString(R.string.drydmghair_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hair_drydmghair_main_four)
        listModel3.descriptionName = context.getString(R.string.hair_drydmghair_detail_four)
        listModel3.image = R.drawable.drydmg_healthyfats
        listModel3.details = context.getString(R.string.drydmghair_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.hair_drydmghair_main_five)
        listModel4.descriptionName = context.getString(R.string.hair_drydmghair_detail_five)
        listModel4.image = R.drawable.reducesugar_dandruff
        listModel4.details = context.getString(R.string.drydmghair_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.hair_drydmghair_main_six)
        listModel5.descriptionName = context.getString(R.string.hair_drydmghair_detail_six)
        listModel5.image = R.drawable.dandruff_water
        listModel5.details = context.getString(R.string.drydmghair_diet_six)
        list.add(listModel5)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.avoid_brushing_toomuch)
        listModel.descriptionName = context.getString(R.string.avoid_brushing_toomuch_detail)
        listModel.image = R.drawable.hairfall_title
        listModel.details = context.getString(R.string.silky_bouncy_avoid_brushing_toomuch)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.washhair_on_off)
        listModel1.descriptionName = context.getString(R.string.washhair_on_off_detail)
        listModel1.image = R.drawable.hairwash
        listModel1.details = context.getString(R.string.silky_bouncy_washhair_on_off)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.right_accessories)
        listModel2.descriptionName = context.getString(R.string.right_accessories_detail)
        listModel2.image = R.drawable.accessories
        listModel2.details = context.getString(R.string.silky_bouncy_right_accessories)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.go_for_layers)
        listModel3.descriptionName = context.getString(R.string.go_for_layers_detail)
        listModel3.image = R.drawable.layers_haircut
        listModel3.details = context.getString(R.string.silky_bouncy_go_for_layers)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.hairspray)
        listModel4.descriptionName = context.getString(R.string.hairspray_detail)
        listModel4.image = R.drawable.hairspray
        listModel4.details = context.getString(R.string.silky_bouncy_hairspray)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.blow_dry)
        listModel5.descriptionName = context.getString(R.string.blow_dry_detail)
        listModel5.image = R.drawable.blowdry
        listModel5.details = context.getString(R.string.silky_bouncy_blow_dry)
        list.add(listModel5)
        val listModel6= ListModel()
        listModel6.title = context.getString(R.string.flip_yourpart)
        listModel6.descriptionName = context.getString(R.string.flip_yourpart_detail)
        listModel6.image = R.drawable.flippart
        listModel6.details = context.getString(R.string.silky_bouncy_flip_yourpart)
        list.add(listModel6)
        val listModel7= ListModel()
        listModel7.title = context.getString(R.string.get_rollin)
        listModel7.descriptionName = context.getString(R.string.get_rollin_detail)
        listModel7.image = R.drawable.valcro_rollers
        listModel7.details = context.getString(R.string.silky_bouncy_get_rollin)
        list.add(listModel7)
        val listModel8= ListModel()
        listModel8.title = context.getString(R.string.beer_surprise)
        listModel8.descriptionName = context.getString(R.string.beer)
        listModel8.image = R.drawable.beer_split
        listModel8.details = context.getString(R.string.silky_bouncy_beer_surprise)
        list.add(listModel8)
        val listModel9= ListModel()
        listModel9.title = context.getString(R.string.additional_tips)
        listModel9.descriptionName = context.getString(R.string.more_tips)
        listModel9.image = R.drawable.silky_shiny_bouncy_title
        listModel9.details = context.getString(R.string.silky_bouncy_additional)
        list.add(listModel9)
        return list
    }
}