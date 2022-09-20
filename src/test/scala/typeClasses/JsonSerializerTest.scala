package chrto.typeClasses.JsonSerializer

import org.scalatest.wordspec.AnyWordSpec
import chrto.dataTypes.JSONValue.JSONString
import chrto.dataTypes.JSONValue.JSONArray
import chrto.dataTypes.JSONValue.JSONNumber
import chrto.dataTypes.JSONValue.JSONObject
import chrto.model.User
import chrto.model.Post
import chrto.model.Feed
import java.util.Date
import chrto.dataTypes.JSONValue.JSONValue

class JsonSerializerTest extends AnyWordSpec {
  "JSON Serializer" when {
    "serialize String value" should {
      import chrto.typeClasses.JsonSerializer.JsonSerializerImplicitInstancies.StringSerializer
      val v: String = "my string"
      val expected: String = "my string"

      "return exact JSON value (Type Class Instance)" in {
        assert(
          StringSerializer.serialize(v) match {
            case JSONString(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONString(expected)}'"
              )
          }
        )
      }

      "return exact JSON value (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(
          v.toJson match {
            case JSONString(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONString(expected)}'"
              )
          }
        )
      }

      "return exact string representation (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(v.toJson.stringify === s""""$expected"""")
      }
    }

    "serialize Int value" should {
      import chrto.typeClasses.JsonSerializer.JsonSerializerImplicitInstancies.NumberSerializer
      val v: Int = 10
      val expected: Int = 10
      "return exact JSON value (Type Class Instance)" in {
        assert(
          NumberSerializer.serialize(v) match {
            case JSONNumber(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONNumber(expected)}'"
              )
          }
        )
      }

      "return exact JSON value (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(
          v.toJson match {
            case JSONNumber(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONNumber(expected)}'"
              )
          }
        )
      }

      "return exact string representation (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(v.toJson.stringify === 10.toString())
      }
    }

    "serialize User value" should {
      import chrto.typeClasses.JsonSerializer.JsonSerializerImplicitInstancies.UserSerializer
      val user: User = new User("Joe", 23, "joe@c.com")
      val expected: Map[String, JSONValue] = Map(
        "name" -> new JSONString("Joe"),
        "age" -> new JSONNumber(23),
        "email" -> new JSONString("joe@c.com")
      )
      "return exact JSON value (Type Class Instance)" in {
        assert(
          UserSerializer.serialize(user) match {
            case JSONObject(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONObject(expected)}'"
              )
          }
        )
      }

      "return exact JSON value (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(
          user.toJson match {
            case JSONObject(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONObject(expected)}'"
              )
          }
        )
      }

      "return exact string representation (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(
          user.toJson.stringify === s"""{"name":"${user.name}","age":${user.age},"email":"${user.email}"}"""
        )
      }
    }

    "serialize Post value" should {
      import chrto.typeClasses.JsonSerializer.JsonSerializerImplicitInstancies.PostSerializer
      val post: Post = new Post("some content", new Date(2022, 9, 20))
      val expected: Map[String, JSONValue] = Map(
        "content" -> new JSONString("some content"),
        "createdAt" -> new JSONString(new Date(2022, 9, 20).toString())
      )
      "return exact JSON value (Type Class Instance)" in {
        assert(
          PostSerializer.serialize(post) match {
            case JSONObject(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONObject(expected)}'"
              )
          }
        )
      }

      "return exact JSON value (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(
          post.toJson match {
            case JSONObject(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONObject(expected)}'"
              )
          }
        )
      }

      "return exact string representation (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(
          post.toJson.stringify === s"""{"content":"${post.content}","createdAt":"${post.createdAt
              .toString()}"}"""
        )
      }
    }

    "serialize Feed value" should {
      import chrto.typeClasses.JsonSerializer.JsonSerializerImplicitInstancies.FeedSerializer
      val feed: Feed = new Feed(
        new User("Joe", 23, "joe@c.com"),
        List(new Post("some content", new Date(2022, 9, 20)))
      )
      val expectedUser: Map[String, JSONValue] = Map(
        "name" -> new JSONString("Joe"),
        "age" -> new JSONNumber(23),
        "email" -> new JSONString("joe@c.com")
      )
      val expectedPost: Map[String, JSONValue] = Map(
        "content" -> new JSONString("some content"),
        "createdAt" -> new JSONString(new Date(2022, 9, 20).toString())
      )
      val expected: Map[String, JSONValue] = Map(
        "user" -> JSONObject(expectedUser),
        "posts" -> JSONArray(List(JSONObject(expectedPost)))
      )

      "return exact JSON value (Type Class Instance)" in {
        assert(
          FeedSerializer.serialize(feed) match {
            case JSONObject(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONObject(expected)}'"
              )
          }
        )
      }

      "return exact JSON value (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        assert(
          feed.toJson match {
            case JSONObject(value) if value === expected => true
            case value =>
              fail(
                s"'$value' has not been expected. Expected value: '${JSONObject(expected)}'"
              )
          }
        )
      }

      "return exact string representation (TypeEnrichment)" in {
        import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment
        import chrto.typeClasses.JsonSerializer.JsonSerializerImplicitInstancies.{
          UserSerializer,
          PostSerializer
        }
        assert(
          feed.toJson.stringify === s"""{"user":${feed.user.toJson.stringify},"posts":${JSONArray(
              feed.posts
                .map((post: Post) => post.toJson)
            ).stringify}}"""
        )
      }
    }
  }
}
