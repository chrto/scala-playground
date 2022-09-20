package chrto.typeClasses.htmlSerializer

import org.scalatest.wordspec.AnyWordSpec
import chrto.typeClasses.htmlSerializer.{HtmlSerializer}
import chrto.typeClasses.htmlSerializer.HtmlSerializerImplicitsInstanciess.{IntSerializer}

class HtmlSerializerTest extends AnyWordSpec {
  "HtmlSerializer" when {
    "serialize Int value" should {
      "return exact string (HtmlSerializer.apply)" in {
        assert(HtmlSerializer[Int].serialize(5) === "<div>5</div>")
      }
      "return exact string (HtmlSerializerEnrichment)" in {
        import chrto.typeClasses.htmlSerializer.TypeEnrichment.{HtmlSerializerEnrichment}
        assert(5.toHtml === "<div>5</div>")
      }
    }
  }
}
