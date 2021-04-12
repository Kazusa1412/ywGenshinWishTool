package com.elouyi.data

import kotlinext.js.jsObject

inline fun buildEchartsOption(block: EchartsOptionBuilder.() -> Unit): dynamic {
    return EchartsOptionBuilder().run {
        block()
        build()
    }
}

class EchartsOptionBuilder: YwBuilder<dynamic> {

    val res: dynamic = jsObject<Any>()

    init {
        res.legend = jsObject()
        res.series = arrayOf<Any>()
    }

    override fun build(): dynamic = res

    fun legend(block: Legend.() -> Unit) {
        res.legend = Legend().run{
            block()
            build()
        }
    }

    fun series(block: Series.() -> Unit) {
        res.series = Series().run{
            block()
            build()
        }
    }
}

class Legend : YwBuilder<dynamic> {
    val res: dynamic = jsObject()

    init {
        res.left = "left"
    }
    override fun build(): dynamic = res

    var orient: dynamic
        get() = res.orient
        set(value) {
        res.orient = value
        }
    var legendData: Array<String>
        get() = res.data
        set(value) {
            res.data = value
        }
}

class Series : YwBuilder<dynamic> {
    var res: dynamic = arrayOf<Any>()


    private var addIndex = 0

    override fun build(): dynamic = res

    fun sets(vararg blocks: SeriesData.() -> Unit) {
        val r = mutableListOf<SeriesData>()
        for (block in blocks) {
            r.add(SeriesData().apply {
                block()
            })
        }
        res = r.map { it.build() }.toTypedArray()

    }

    class SeriesData : YwBuilder<dynamic> {
        val res: dynamic = jsObject()

        init {
            res.data = arrayOf<Any>()

        }


        var type: String
            get() = res.type
            set(value) {
                res.type = value
            }

        var left: String
            get() = res.left
            set(value) {
                res.left = value
            }

        var top: String
            get() = res.top
            set(value) {
                res.top = value
            }

        var width: String
            get() = res.width
            set(value) {
                res.width = value
            }


        fun seriesDatas(vararg blocks: Data.() -> Unit) {
            val r = mutableListOf<Data>()
            for (block in blocks) {
                r.add(Data().apply {
                    block()
                })
            }
            res.data = r.map { it.build() }.toTypedArray()
        }



        override fun build(): dynamic = res

        class Data: YwBuilder<dynamic> {

            val res: dynamic = jsObject()
            override fun build(): dynamic = res

            var value: Int
                get() = res.value
                set(value) {
                    res.value = value
                }

            var name: String
                get() = res.name
                set(value) {
                    res.name = value
                }
        }
    }
}