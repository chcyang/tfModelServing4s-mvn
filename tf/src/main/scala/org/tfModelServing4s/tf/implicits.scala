package org.tfModelServing4s
package tf

import java.nio.FloatBuffer

import org.tensorflow.Tensor
import org.tfModelServing4s.dsl._

object implicits {

  implicit val stringEncoder = new TensorEncoder[Tensor[_], Array[Byte]] {

    def toTensor(data: Array[Byte], shape: List[Long]) =
      Tensor.create(data).asInstanceOf[Tensor[java.lang.Float]]
  }

  implicit val float1DimArrayEncoder = new TensorEncoder[Tensor[_], Array[Float]] {

    def toTensor(data: Array[Float], shape: List[Long]) =
      Tensor.create(shape.toArray, FloatBuffer.wrap(data))
  }

  implicit val float2DimArrayEncoder = new TensorEncoder[Tensor[_], Array[Array[Float]]] {

    def toTensor(data: Array[Array[Float]], shape: List[Long]) =
      Tensor.create(shape.toArray, FloatBuffer.wrap(data.flatten))
  }

  implicit val float1DimArrayDecoder = new TensorDecoder[Tensor[_], Array[Float]] {

    def fromTensor(tensor: Tensor[_]) = {
      val shape = tensor.shape().toList.map(_.toInt)
      val array = Array.ofDim[Float](shape.head)
      tensor.copyTo(array)

      array
    }
  }

  implicit val float2DimArrayDecoder = new TensorDecoder[Tensor[_], Array[Array[Float]]] {

    def fromTensor(tensor: Tensor[_]) = {
      val shape = tensor.shape().toList.map(_.toInt)
      val array = Array.ofDim[Float](shape.head, shape(1))
      tensor.copyTo(array)

      array
    }
  }

  implicit val closeableTensor = new Closeable[Tensor[_]] {

    def close(resource: Tensor[_]): Unit = {

      println("releasing TF tensor")
      resource.close()
    }

  }

  implicit val closeableModel = new Closeable[TFModel] {

    def close(resource: TFModel): Unit = {

      println("closing TF model")
      resource.bundle.close()
    }

  }

}
