package chrto.typeClasses.JsonSerializer

import chrto.dataTypes.JSONValue.JSONValue
import chrto.dataTypes.JSONValue.JSONString
import chrto.dataTypes.JSONValue.JSONNumber
import chrto.dataTypes.JSONValue.JSONArray
import chrto.dataTypes.JSONValue.JSONObject
import chrto.model.User
import chrto.model.Post
import chrto.model.Feed

// Type Class
trait JsonSerializer[T] {
  def serialize(value: T): JSONValue
}

// Implicit Type Class Instancies
object JsonSerializerImplicitInstancies {
  import chrto.typeClasses.JsonSerializer.TypeEnrichment.JsonSerializerEnrichment

  implicit object StringSerializer extends JsonSerializer[String] {
    override def serialize(value: String): JSONValue = JSONString(value)
  }


  implicit object NumberSerializer extends JsonSerializer[Int] {
    override def serialize(value: Int): JSONValue = JSONNumber(value)
  }

  implicit object UserSerializer extends JsonSerializer[User] {
    override def serialize(user: User): JSONValue = JSONObject(
      Map(
        "name" -> JSONString(user.name),
        "age" -> JSONNumber(user.age),
        "email" -> JSONString(user.email)
      )
    )
  }

  implicit object PostSerializer extends JsonSerializer[Post] {
    override def serialize(post: Post): JSONValue = JSONObject(
      Map(
        "content" -> JSONString(post.content),
        "createdAt" -> JSONString(post.createdAt.toString())
      )
    )
  }

  implicit object FeedSerializer extends JsonSerializer[Feed] {
    override def serialize(feed: Feed): JSONValue = JSONObject(
      Map(
        "user" -> feed.user.toJson,
        "posts" -> JSONArray(
          feed.posts.map((post: Post) => post.toJson)
        )
      )
    )
  }
}

object TypeEnrichment {
  implicit class JsonSerializerEnrichment[T](value: T) {
    def toJson(implicit instance: JsonSerializer[T]): JSONValue =
      instance.serialize(value)
  }
}
