package org.tfModelServing4s.utils
package show

object implicits {

  private def arrayToString[A](a: Array[A]): String = a.mkString("[", ", ", "]")

  implicit def toStringShow[A] = new Show[A] {

    def shows(a: A) = a.toString

  }

  implicit def show1DimArray[A](implicit S: Show[A]) = new Show[Array[A]] {

    def shows(a: Array[A]) = arrayToString(a.map(S.shows))

  }

  implicit def show2DimArray[A](implicit S1Dim: Show[Array[A]], S: Show[Array[String]]) = new Show[Array[Array[A]]] {

    def shows(a: Array[Array[A]]) = S.shows(a.map(S1Dim.shows))

  }

  implicit def show3DimArray[A](implicit S1Dim: Show[Array[Array[A]]], S: Show[Array[String]])
  = new Show[Array[Array[Array[A]]]] {

    def shows(a: Array[Array[Array[A]]]) = S.shows(a.map(S1Dim.shows))

  }

  implicit def show4DimArray[A](implicit S1Dim: Show[Array[Array[Array[A]]]], S: Show[Array[String]])
  = new Show[Array[Array[Array[Array[A]]]]] {

    def shows(a: Array[Array[Array[Array[A]]]]) = S.shows(a.map(S1Dim.shows))

  }

  implicit def show5DimArray[A](implicit S1Dim: Show[Array[Array[Array[Array[A]]]]], S: Show[Array[String]])
  = new Show[Array[Array[Array[Array[Array[A]]]]]] {

    def shows(a: Array[Array[Array[Array[Array[A]]]]]) = S.shows(a.map(S1Dim.shows))

  }
}
