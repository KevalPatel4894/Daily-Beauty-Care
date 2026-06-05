package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object FrizzyHair {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.almondoil_egg)
        listModel.descriptionName = context.getString(R.string.forty_min)
        listModel.image = R.drawable.almondoil_egg
        listModel.details = context.getString(R.string.frizzy_almondoil_egg)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.avocado_mask)
        listModel1.descriptionName = context.getString(R.string.forty_min)
        listModel1.image = R.drawable.avocado_split
        listModel1.details = context.getString(R.string.frizzy_avocado_mask)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.coconutoil_vitamine)
        listModel2.descriptionName = context.getString(R.string.forty_min)
        listModel2.image = R.drawable.coconutoil_hairfall_remedy
        listModel2.details = context.getString(R.string.frizzy_coconutoil_vitamine)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.mayonnaise)
        listModel3.descriptionName = context.getString(R.string.ten_min)
        listModel3.image = R.drawable.mayonnaise_split
        listModel3.details = context.getString(R.string.frizzy_mayonnaise)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.banana)
        listModel4.descriptionName = context.getString(R.string.twentyfive_min)
        listModel4.image = R.drawable.banana_split
        listModel4.details = context.getString(R.string.frizzy_banana)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.beer)
        listModel5.descriptionName = context.getString(R.string.five_min)
        listModel5.image = R.drawable.beer_split
        listModel5.details = context.getString(R.string.frizzy_beer)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.lemon_honey)
        listModel6.descriptionName = context.getString(R.string.fifteen_min)
        listModel6.image = R.drawable.honey_lemonpeel
        listModel6.details = context.getString(R.string.frizzy_lemon_honey)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.honey)
        listModel7.descriptionName = context.getString(R.string.thirty_min)
        listModel7.image = R.drawable.honey
        listModel7.details = context.getString(R.string.frizzy_honey)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.yogurt)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.dryhands_yogurt
        listModel8.details = context.getString(R.string.frizzy_yogurt)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.coconut_milk)
        listModel9.descriptionName = context.getString(R.string.thirty_min)
        listModel9.image = R.drawable.eyelashes_coconutmilk
        listModel9.details = context.getString(R.string.frizzy_coconutmilk)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.apple_cider_vinegar)
        listModel10.descriptionName = context.getString(R.string.five_min)
        listModel10.image = R.drawable.apple_vinegar_dandruff
        listModel10.details = context.getString(R.string.straight_apple_cider_vinegor)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.banana_curd_oliveoil)
        listModel11.descriptionName = context.getString(R.string.thirty_min)
        listModel11.image = R.drawable.banana_curd_oliveoil
        listModel11.details = context.getString(R.string.straight_banana_curd_oliveoil)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.aloe_vera)
        listModel12.descriptionName = context.getString(R.string.forty_min)
        listModel12.image = R.drawable.aloevera_split
        listModel12.details = context.getString(R.string.straight_aloevera)
        list.add(listModel12)
        val listModel13 = ListModel()
        listModel13.title = context.getString(R.string.hotoil_massage)
        listModel13.descriptionName = context.getString(R.string.fortyfive_min)
        listModel13.image = R.drawable.hotoil_split
        listModel13.details = context.getString(R.string.frizzy_hotoil_massage)
        list.add(listModel13)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.salmon)
        listModel.descriptionName = context.getString(R.string.healthy_fats)
        listModel.image = R.drawable.hairfall_salmon_diet
        listModel.details = context.getString(R.string.frizzy_salmon)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.citrus_fruits)
        listModel1.descriptionName = context.getString(R.string.oranges_lemon_lime_grapefruit)
        listModel1.image = R.drawable.hairfall_carrot_diet
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
        listModel.title = context.getString(R.string.shenxue_accupressure)
        listModel.descriptionName = context.getString(R.string.accupressure_point_palm_side)
        listModel.image = R.drawable.shenxue_split
        listModel.details = context.getString(R.string.splitend_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.rubbing_nails)
        listModel1.descriptionName = context.getString(R.string.balyam_yoga)
        listModel1.image = R.drawable.vajrasana
        listModel1.details = context.getString(R.string.splitend_exercise_four)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.accupressure_massage)
        listModel2.descriptionName = context.getString(R.string.best_results_olive_oil)
        listModel2.image = R.drawable.drydmg_accupresuremsg
        listModel2.details = context.getString(R.string.splitend_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.good_haircut)
        listModel3.descriptionName = context.getString(R.string.haircut)
        listModel3.image = R.drawable.haircut
        listModel3.details = context.getString(R.string.frizzy_good_haircut)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.anti_frizz_products)
        listModel4.descriptionName = context.getString(R.string.products)
        listModel4.image = R.drawable.antifrizzproducts
        listModel4.details = context.getString(R.string.frizzy_anti_frizz_products)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.what_to_avoid)
        listModel5.descriptionName = context.getString(R.string.avoid)
        listModel5.image = R.drawable.avoid
        listModel5.details = context.getString(R.string.frizzy_what_to_avoid)
        list.add(listModel5)
        return list
    }
}