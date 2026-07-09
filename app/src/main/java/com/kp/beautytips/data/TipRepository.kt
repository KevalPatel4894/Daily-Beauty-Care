package com.kp.beautytips.data

import android.content.Context
import com.kp.beautytips.model.ListModel
import org.json.JSONArray
import java.io.InputStream
import java.nio.charset.Charset

object TipRepository {
    private var isInitialized = false

    private val subcategoryMap = HashMap<String, SubCategoryData>()
    private val allTipsList = ArrayList<ListModel>()

    class SubCategoryData(
        val subcategoryKey: String,
        val nameRes: String,
        val imageRes: String,
        val remedies: List<ListModel>,
        val diets: List<ListModel>,
        val exercises: List<ListModel>
    )

    fun initialize(context: Context) {
        if (isInitialized) return
        try {
            val jsonString = loadJsonFromAsset(context, "tips_data.json") ?: return
            val categoriesArray = JSONArray(jsonString)
            
            for (i in 0 until categoriesArray.length()) {
                val categoryObj = categoriesArray.getJSONObject(i)
                val subcategoriesArray = categoryObj.getJSONArray("subcategories")
                
                for (j in 0 until subcategoriesArray.length()) {
                    val subcategoryObj = subcategoriesArray.getJSONObject(j)
                    val subcategoryKey = subcategoryObj.getString("subcategoryKey")
                    val nameRes = subcategoryObj.getString("nameRes")
                    val imageRes = subcategoryObj.getString("imageRes")
                    
                    val remedies = parseTips(context, subcategoryObj.optJSONArray("remedies") ?: JSONArray())
                    val diets = parseTips(context, subcategoryObj.optJSONArray("diets") ?: JSONArray())
                    val exercises = parseTips(context, subcategoryObj.optJSONArray("exercises") ?: JSONArray())
                    
                    val subcategoryData = SubCategoryData(
                        subcategoryKey = subcategoryKey,
                        nameRes = nameRes,
                        imageRes = imageRes,
                        remedies = remedies,
                        diets = diets,
                        exercises = exercises
                    )
                    
                    subcategoryMap[subcategoryKey] = subcategoryData
                    
                    allTipsList.addAll(remedies)
                    allTipsList.addAll(diets)
                    allTipsList.addAll(exercises)
                }
            }
            
            val uniqueTips = ArrayList<ListModel>()
            val titlesSeen = HashSet<String>()
            for (tip in allTipsList) {
                if (titlesSeen.add(tip.title)) {
                    uniqueTips.add(tip)
                }
            }
            allTipsList.clear()
            allTipsList.addAll(uniqueTips)
            
            isInitialized = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseTips(context: Context, jsonArray: JSONArray): List<ListModel> {
        val tipsList = ArrayList<ListModel>()
        for (i in 0 until jsonArray.length()) {
            val tipObj = jsonArray.getJSONObject(i)
            val titleRes = tipObj.optString("titleRes", "")
            val durationRes = tipObj.optString("durationRes", "")
            val imageRes = tipObj.optString("imageRes", "")
            val detailsRes = tipObj.optString("detailsRes", "")
            
            val listModel = ListModel()
            
            // Resolve Title
            if (titleRes.startsWith("RAW:")) {
                listModel.title = titleRes.substring(4)
            } else if (titleRes.isNotEmpty()) {
                val resId = context.resources.getIdentifier(titleRes, "string", context.packageName)
                listModel.title = if (resId != 0) context.getString(resId) else titleRes
            }
            
            // Resolve Duration
            if (durationRes.startsWith("RAW:")) {
                listModel.descriptionName = durationRes.substring(4)
            } else if (durationRes.isNotEmpty()) {
                val resId = context.resources.getIdentifier(durationRes, "string", context.packageName)
                listModel.descriptionName = if (resId != 0) context.getString(resId) else durationRes
            }
            
            // Resolve Image
            if (imageRes.isNotEmpty()) {
                val resId = context.resources.getIdentifier(imageRes, "drawable", context.packageName)
                listModel.image = resId
            }
            
            // Resolve Details
            if (detailsRes.startsWith("RAW:")) {
                listModel.details = detailsRes.substring(4)
            } else if (detailsRes.isNotEmpty()) {
                val resId = context.resources.getIdentifier(detailsRes, "string", context.packageName)
                listModel.details = if (resId != 0) context.getString(resId) else detailsRes
            }
            
            tipsList.add(listModel)
        }
        return tipsList
    }

    fun getRemedies(context: Context, subcategoryKey: String): ArrayList<ListModel> {
        initialize(context)
        return ArrayList(subcategoryMap[subcategoryKey]?.remedies ?: emptyList())
    }

    fun getDiets(context: Context, subcategoryKey: String): ArrayList<ListModel> {
        initialize(context)
        return ArrayList(subcategoryMap[subcategoryKey]?.diets ?: emptyList())
    }

    fun getExercises(context: Context, subcategoryKey: String): ArrayList<ListModel> {
        initialize(context)
        return ArrayList(subcategoryMap[subcategoryKey]?.exercises ?: emptyList())
    }

    fun getAllTips(context: Context): ArrayList<ListModel> {
        initialize(context)
        return ArrayList(allTipsList)
    }

    fun getSubCategoryKeyByName(context: Context, localizedName: String): String? {
        initialize(context)
        for ((key, data) in subcategoryMap) {
            val resId = context.resources.getIdentifier(data.nameRes, "string", context.packageName)
            if (resId != 0) {
                val name = context.getString(resId)
                if (name.equals(localizedName, ignoreCase = true)) {
                    return key
                }
            }
        }
        return null
    }
}
