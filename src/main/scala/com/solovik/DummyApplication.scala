package com.solovik

import java.io.File

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

/**
  * @author Arun Manivannan
  */
object DummyApplication extends App {

  println("**************")
  val fs = FileSystem.get(new Configuration())
  val status = fs.listStatus(new Path("hdfs://sandbox-hdp.hortonworks.com:8020/tmp/solovik/data"))
  status.foreach(x=> println(x.getPath))

  println("**************")
  getListOfFiles("/tmp/solovik/data").foreach(f => println(f.getAbsolutePath))
  println("**************")

  var i = 10
  while(i > 0) {
    println("Niceeeeeeeeee !!! This is the core application that is running within the container that got negotiated by from Application Master !!!")
    Thread.sleep(1000)
    i-=1
  }

  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      println("********** else")
      List[File]()
    }
  }
}
