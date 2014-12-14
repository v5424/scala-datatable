/**
The MIT License (MIT)

Copyright (c) 2014 Martin Cooper

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. */

package com.github.martincooper.datatable

import scala.util.{ Try, Success }

/** DataTable class. Handles the immutable storage of data in a Row / Column format. */
class DataTable(tableName: String, dataColumns: Iterable[GenericColumn]) {

  def name = tableName
  def columns = dataColumns.toIndexedSeq

  /** Returns the data column at the selected index. */
  def apply(index: Int) = {
    Try(columns(index)) match {
      case Success(col) => Some(col)
      case _ => None
    }
  }

  /** Returns the column with the specified name. */
  def apply(columnName: String) = columns.find(_.name == columnName)

  /** Outputs a more detailed toString implementation. */
  override def toString = {
    val tableDetails = "DataTable:" + name + "[Rows:" + columns.head.data.length + "]"
    val colDetails = columns.map(col => "[" + col.toString + "]").mkString(" ")

    tableDetails + colDetails
  }
}
