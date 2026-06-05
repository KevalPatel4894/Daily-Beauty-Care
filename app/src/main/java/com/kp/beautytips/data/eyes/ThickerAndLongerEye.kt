package com.kp.beautytips.data.eyes

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object ThickerAndLongerEye {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.castor_oil)
        listModel.descriptionName = context.getString(R.string.overnight)
        listModel.image = R.drawable.eyebrows_castor
        listModel.details = context.getString(R.string.eyelashes_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.olive_oil)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.lice_oliveoil
        listModel1.details = context.getString(R.string.eyelashes_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.aloe_vera)
        listModel2.descriptionName = context.getString(R.string.overnight)
        listModel2.image = R.drawable.aloevera_split
        listModel2.details = context.getString(R.string.eyelashes_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.lemon_peels)
        listModel3.descriptionName = context.getString(R.string.overnight)
        listModel3.image = R.drawable.eyelashes_lemonpeel
        listModel3.details = context.getString(R.string.eyelashes_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.egg)
        listModel4.descriptionName = context.getString(R.string.fifteen_min)
        listModel4.image = R.drawable.puffy_eggwhite
        listModel4.details = context.getString(R.string.eyelashes_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.green_tea)
        listModel5.descriptionName = context.getString(R.string.fifteen_min)
        listModel5.image = R.drawable.eyelashes_greentea
        listModel5.details = context.getString(R.string.eyelashes_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.coconut_milk)
        listModel6.descriptionName = context.getString(R.string.ten_min)
        listModel6.image = R.drawable.eyelashes_coconutmilk
        listModel6.details = context.getString(R.string.eyelashes_remedy_seven)
        list.add(listModel6)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.eyes_eyelashes_main_two)
        listModel1.descriptionName = context.getString(R.string.eyes_eyelashes_detail_two)
        listModel1.image = R.drawable.vitaminb_dandruff
        listModel1.details = context.getString(R.string.eyelashes_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.eyes_eyelashes_main_three)
        listModel2.descriptionName = context.getString(R.string.eyes_eyelashes_detail_three)
        listModel2.image = R.drawable.eyebrows_iron
        listModel2.details = context.getString(R.string.eyelashes_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.eyes_eyelashes_main_four)
        listModel3.descriptionName = context.getString(R.string.eyes_eyelashes_detail_four)
        listModel3.image = R.drawable.eyelashes_folicacid
        listModel3.details = context.getString(R.string.eyelashes_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.eyes_eyelashes_main_five)
        listModel4.descriptionName = context.getString(R.string.eyes_eyelashes_detail_five)
        listModel4.image = R.drawable.eyelashes_salmon
        listModel4.details = context.getString(R.string.eyelashes_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.eyes_eyelashes_main_six)
        listModel5.descriptionName = context.getString(R.string.eyes_eyelashes_detail_six)
        listModel5.image = R.drawable.eyelashes_calcium
        listModel5.details = context.getString(R.string.eyelashes_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.eyes_eyelashes_main_seven)
        listModel6.descriptionName = context.getString(R.string.eyes_eyelashes_detail_seven)
        listModel6.image = R.drawable.beautifuleyes_vitaminc
        listModel6.details = context.getString(R.string.eyelashes_diet_seven)
        list.add(listModel6)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.brush_your_eyelashes)
        listModel.descriptionName = context.getString(R.string.brush_eyelashes)
        listModel.image = R.drawable.eyelashes_brush
        listModel.details = context.getString(R.string.eyelashes_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.eyelid_massage)
        listModel1.descriptionName = context.getString(R.string.massage_your_eyelids)
        listModel1.image = R.drawable.eyelashes_eyelidmassage
        listModel1.details = context.getString(R.string.eyelashes_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.gently_remove_makeup)
        listModel2.descriptionName = context.getString(R.string.removing_makeup)
        listModel2.image = R.drawable.eyelashes_removemakeup
        listModel2.details = context.getString(R.string.eyelashes_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.additional_tips)
        listModel3.descriptionName = context.getString(R.string.more_tips)
        listModel3.image = R.drawable.eyelashes_title
        listModel3.details = context.getString(R.string.eyelashes_exercise_four)
        list.add(listModel3)
        return list
    }
}