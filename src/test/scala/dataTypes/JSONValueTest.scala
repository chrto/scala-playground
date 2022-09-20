package chrto.dataTypes.JSONValue

import org.scalatest.wordspec.AnyWordSpec
import java.util.Date

class JSONValueTest extends AnyWordSpec {
  "JSON value" when {
    "value is a String" should {
      "return exact string value enclosed in double quotes" in {
        assert(JSONString("test").stringify === "\"test\"")
      }
    }

    "value is a Date" should {
      "return exact string value enclosed in double quotes" in {
        assert(
          JSONDate(
            new Date(2022, 8, 20)
          ).stringify === "\"Wed Sep 20 00:00:00 CEST 3922\""
        )
      }
    }

    "value is an Int" should {
      "return exact string value" in {
        assert(JSONNumber(5).stringify === "5")
      }
    }

    "value is a List" should {
      "return exact string value" in {
        assert(
          JSONArray(
            List(
              JSONString("my string value"),
              JSONNumber(15)
            )
          ).stringify === """["my string value",15]"""
        )
      }
    }

    "value is an Object" should {
      "return exact string value" in {
        assert(
          JSONObject(
            Map(
              "name" -> JSONString("Joe"),
              "age" -> JSONNumber(34),
              "friends" -> JSONArray(
                List(
                  JSONObject(
                    Map(
                      "name" -> JSONString("Jack"),
                      "age" -> JSONNumber(37),
                      "friends" -> JSONArray(List())
                    )
                  )
                )
              )
            )
          ).stringify === """{"name":"Joe","age":34,"friends":[{"name":"Jack","age":37,"friends":[]}]}"""
        )
      }
    }
  }
}
