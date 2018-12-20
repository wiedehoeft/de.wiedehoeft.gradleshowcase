package de.wiedehoeft.pdfcreation.pdf;

class Point {

  final int x;
  final int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "Point{" +
      "x=" + x +
      ", y=" + y +
      '}';
  }
}
