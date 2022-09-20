package chrto.dataTypes.JSONValue

import java.util.Date

// intermediate data types
sealed trait JSONValue {
  def stringify: String
}

final case class JSONString(value: String) extends JSONValue {
  def stringify: String = s"\"$value\""
}

final case class JSONDate(value: Date) extends JSONValue {
  def stringify: String = JSONString(value.toString()).stringify
}

final case class JSONNumber(value: Int) extends JSONValue {
  def stringify: String = value.toString()
}

final case class JSONArray(values: List[JSONValue]) extends JSONValue {
  def stringify: String =
    values.map((value: JSONValue) => value.stringify).mkString("[", ",", "]")
}

final case class JSONObject(values: Map[String, JSONValue]) extends JSONValue {
  def stringify: String = values
    .map((key_val: (String, JSONValue)) =>
      key_val match {
        case (key, value) => s""""${key}\":${value.stringify}"""
      }
    )
    .mkString("{", ",", "}")
}
