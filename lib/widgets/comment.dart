import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/constants/size.dart';
import 'package:team_swan_project_20200313/utils/profile_img_path.dart';

class Comment extends StatelessWidget {

  final String username;
  final bool showProfile;
  final DateTime dateTime;
  final String caption;

  const Comment({Key key, @required this.username, this.showProfile = false, this.dateTime, @required this.caption}) : super(key: key);



  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Visibility(
          visible: showProfile,
          child: CircleAvatar(
            backgroundImage: NetworkImage(
              getProfileImgPath(username)
            ),
            radius: profile_radius,
          ),
        ),
        Visibility(
            visible: showProfile,
            child: SizedBox(width: common_xs_gap)),
        Expanded(  /// Row 안에서 Visibility 아바타가 차지하고 남은 부분은 아래 컬럼이 차지하게 해주라
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              RichText(
                text: TextSpan(
                    style: DefaultTextStyle.of(context).style, /// context는 현재 app의 상태값이고 해당 메소드는 그 상태값에서 기본 텍스트 스타일의 스타일을 가지고 온다.
                    children: <TextSpan>[
                      TextSpan(
                          text: username,
                          style: TextStyle(fontWeight: FontWeight.bold)
                      ),
                      TextSpan(
                          text: " "
                      ),
                      TextSpan(
                          text: caption
                      ),

                    ]
                ),
              ),
              SizedBox(height: common_xxxs_gap,),
              Visibility(
                visible: dateTime != null,
                  child: dateTime == null? SizedBox() : Text(dateTime.toIso8601String(), style: TextStyle(color: Colors.grey[600], fontSize: 10),)
              )
            ],
          ),
        )
        
      ],
    );
  }
}
