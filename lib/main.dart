import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/screens/auth_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.deepOrange,
      ),
      home: AuthPage(),
    );
  }
}
