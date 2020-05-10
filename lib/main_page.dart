import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/constants/size.dart';
import 'package:team_swan_project_20200313/screens/feed_page.dart';
import 'package:team_swan_project_20200313/screens/profile_page.dart';

class MainPage extends StatefulWidget {
  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  int _selectedIndex = 0;

  static List<Widget> _widgetOptions = <Widget>[
    FeedPage(),
    Container(
      color: Colors.primaries[2],
    ),
    Container(
      color: Colors.primaries[2],
    ),
    Container(
      color: Colors.primaries[3],
    ),
    ProfilePage(),
  ];

  @override
  Widget build(BuildContext context) {
    if (size == null) {
      size = MediaQuery.of(context).size;
    }
    return Scaffold(
        body: IndexedStack(
          /// IndexedStack은 List<Widget>과 다르게, 미리 다 위젯을 준비해놓고 보여주지만, 후자는 매번 새로 생성하게 된다.
          index: _selectedIndex,
          children: _widgetOptions,
        ),
        bottomNavigationBar: BottomNavigationBar(
          showSelectedLabels: false,
          showUnselectedLabels: false,
          unselectedItemColor: Colors.grey[900],
          selectedItemColor: Colors.black,
          type: BottomNavigationBarType.fixed,
          backgroundColor: Color.fromRGBO(249, 249, 249, 1),
          items: <BottomNavigationBarItem>[
            _bulidBottomNavigationBarItem(
                activeIconPath: "assets/home_selected.png",
                iconPath: "assets/home.png"),
            _bulidBottomNavigationBarItem(
                activeIconPath: "assets/search_selected.png",
                iconPath: "assets/search.png"),
            _bulidBottomNavigationBarItem(iconPath: "assets/add.png"),
            _bulidBottomNavigationBarItem(
                activeIconPath: "assets/heart_selected.png",
                iconPath: "assets/heart.png"),
            _bulidBottomNavigationBarItem(
                activeIconPath: "assets/profile_selected.png",
                iconPath: "assets/profile.png"),
          ],
          currentIndex: _selectedIndex,
          onTap: (index) => _onItemTapped(index),
        ));
  }

  BottomNavigationBarItem _bulidBottomNavigationBarItem(
      {String activeIconPath, String iconPath}) {
    return BottomNavigationBarItem(
      activeIcon:
      activeIconPath == null ? null : ImageIcon(AssetImage(activeIconPath)),
      icon: ImageIcon(AssetImage(iconPath)),
      title: Text(''),
    );
  }

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }
}
