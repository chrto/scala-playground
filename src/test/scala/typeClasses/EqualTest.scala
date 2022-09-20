package chrto.typeClasses.equalizer

import org.scalatest.wordspec.AnyWordSpec
import chrto.typeClasses.equalizer.Equal

class EqualTest extends AnyWordSpec {
  "Equal" when {
    "compare Int value" should {
      "return true, if two integers are same (Instantiate by Equal companion object -> Equal.apply)" in {
        import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{IntEqualizer}
        assert(Equal(5, 5))
      }

      "return true, if two integers are same (EqualEnrichment)" in {
        import  chrto.typeClasses.equalizer.TypeEnrichment.{EqualEnrichment}
        import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{IntEqualizer}
        assert(new EqualEnrichment[Int](5).===(5))
      }

      "return false, if two integers are same (EqualEnrichment)" in {
        import  chrto.typeClasses.equalizer.TypeEnrichment.{EqualEnrichment}
        import chrto.typeClasses.equalizer.EqualierImplicitsInstanciess.{IntEqualizer}
        assert(!new EqualEnrichment[Int](5).!==(5))
      }
    }
  }
}
