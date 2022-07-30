package com.rebin.appies.network

data class ResponseModel(
    val dataObject: List<DataObject>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)

data class DataObject(
    val myHierarchy: List<MyHierarchy>
)

data class MyHierarchy(
    val heirarchyList: List<Heirarchy>
)

data class Heirarchy(
    val contactName: String,
    val contactNumber: String,
    val designationName: String
)