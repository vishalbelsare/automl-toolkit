package com.databricks.labs.automl.pyspark

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.spark.sql.DataFrame
import com.databricks.labs.automl.executor.config.{ConfigurationGenerator, InstanceConfig}
import com.databricks.labs.automl.executor.FamilyRunner
import org.apache.spark.sql.SparkSession

object FamilyRunnerUtil {
  lazy val objectMapper = new ObjectMapper()
  def runFamilyRunner(configs:String,
                      predictionType: String,
                      df: DataFrame): Unit = {

    val firstMap = jsonToMap(configs)
    val familyRunnerConfigs = buildArray(firstMap,
      predictionType)
    //run the family runner
    val runner = FamilyRunner(df, familyRunnerConfigs).executeWithPipeline()
//    runner.bestMlFlowRunId.toSeq.toDF("modelFamily", "bestMlFlowRunId").createOrReplaceTempView("bestMlFlowRunId")
    runner.familyFinalOutput.modelReportDataFrame.createOrReplaceTempView("modelReportDataFrame")
    runner.familyFinalOutput.generationReportDataFrame.createOrReplaceTempView("generationReportDataFrame")
  }

  def buildArray(configs: Map[String, Any],
                 predictionType: String): Array[InstanceConfig] = {

    configs
      .asInstanceOf[Map[String, Map[String, Any]]]
      .map({
        case (key, valuesMap) => {
          ConfigurationGenerator.generateConfigFromMap(key, predictionType, valuesMap)
        }
      })
      .toArray
  }
  def jsonToMap(message: String): Map[String, Any] = {
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper.readValue(message, classOf[Map[String, Any]])
  }

}
