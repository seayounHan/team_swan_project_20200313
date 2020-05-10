import 'package:flutter/material.dart';

/// of의 param인 context는 Scaffold의 context만 가능하다.
void simpleSnackbar(BuildContext context, String txt){
  final snackBar = SnackBar(content: Text(txt),);
  Scaffold.of(context).showSnackBar(snackBar);
}