package com.databricks.labs.automl.utilities

case class SchemaNamesTypes(name: String, dataType: String)

case class ModelDetectionSchema(a: Double,
                                label: Double,
                                automl_internal_id: Long)

case class OutlierTestSchema(a: Double,
                             b: Double,
                             c: Double,
                             label: Int,
                             automl_internal_id: Long)

case class NaFillTestSchema(dblData: Double,
                            fltData: Float,
                            intData: Int,
                            ordinalIntData: Int,
                            strData: String,
                            boolData: Boolean,
                            dateData: String,
                            label: Int,
                            automl_internal_id: Long)

case class VarianceTestSchema(a: Double,
                              b: Double,
                              c: Double,
                              d: Int,
                              label: Int,
                              automl_internal_id: Long)

case class PearsonTestSchema(positiveCorr1: Int,
                             positiveCorr2: Int,
                             noFilter1: Double,
                             noFilter2: Int,
                             label: Int,
                             automl_internal_id: Long)

case class PearsonRegressionTestSchema(positiveCorr1: Double,
                                       positiveCorr2: Double,
                                       positiveCorr3: Int,
                                       noFilter1: Double,
                                       noFilter2: Int,
                                       label: Double,
                                       automl_internal_id: Long)
