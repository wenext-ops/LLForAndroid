package com.tenext.auth.entity

class PanelProperty {

    //    var big = false
    var id = ""
    var ui = UI()

    fun isBig(): Boolean {
        return ui.type == "btn-big"
    }

    class UI {
        var type = ""
        var icon = ""
    }

}