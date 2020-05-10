import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/constants/size.dart';
import 'package:team_swan_project_20200313/utils/profile_img_path.dart';
import 'package:team_swan_project_20200313/widgets/comment.dart';
import 'package:team_swan_project_20200313/widgets/my_progress_indicator.dart';

class FeedPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Image.asset(
            'assets/swan_appbar.png',
            height: 60,
          ),
          actions: <Widget>[
            IconButton(
                onPressed: null,
                icon: ImageIcon(
                  AssetImage('assets/actionbar_camera.png'),
                  color: Colors.black,
                )),
            IconButton(
                onPressed: null,
                icon: ImageIcon(
                  AssetImage('assets/direct_message.png'),
                  color: Colors.black,
                )),
          ],
        ),
        body: ListView.builder(
          itemCount: 15,
          itemBuilder: (BuildContext context, int index) {
            return _postItem(index, context);
          },
        ));
  }

  Column _postItem(int index, BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        _postHeader('username $index'),
        _postImage(index),
        _postActions(),
        _postCaption(context, index),
        /// 아래 아이콘이 눌려지도록 하는데는 GestureDetector or FlatButton을 활용하면 된다.
        _allComments()
      ],
    );
  }

  FlatButton _allComments() => FlatButton(onPressed:null, child: Text('show all 18 comments',style: TextStyle(color: Colors.grey[600]),));

  Padding _postCaption(BuildContext context, int index) {
    return Padding(
        padding: const EdgeInsets.symmetric(
            horizontal: common_gap, vertical: common_xs_gap),
        child: Comment(
          dateTime: DateTime.now(),
          showProfile: true,
          username: 'username $index',
          caption: 'I love summer so~~~~~~~~~~~~~~~ much~~~~~~~~',
        ));
  }

  Row _postActions() {
    return Row(
      children: <Widget>[
        IconButton(
          icon: ImageIcon(
            AssetImage('assets/bookmark.png'),
            color: Colors.black87,
          ),
          onPressed: null,
        ),
        IconButton(
          icon: ImageIcon(
            AssetImage('assets/comment.png'),
            color: Colors.black87,
          ),
          onPressed: null,
        ),
        IconButton(
          icon: ImageIcon(
            AssetImage('assets/direct_message.png'),
            color: Colors.black87,
          ),
          onPressed: null,
        ),
        Spacer(),
        IconButton(
          icon: ImageIcon(
            AssetImage('assets/heart_selected.png'),
            color: Colors.redAccent,
          ),
          onPressed: null,
        )
      ],
    );
  }

  Row _postHeader(String username) {
    return Row(
      children: <Widget>[
        Padding(
          padding: const EdgeInsets.all(common_gap),
          child: CircleAvatar(
            backgroundImage:
                CachedNetworkImageProvider(getProfileImgPath(username)),
            radius: profile_radius,
          ),
        ),
        Expanded(child: Text(username)),
        IconButton(
          icon: Icon(
            Icons.more_horiz,
            color: Colors.black87,
          ),
          onPressed: null,
        )
      ],
    );
  }

  CachedNetworkImage _postImage(int index) {
    return CachedNetworkImage(
      imageUrl: 'https://picsum.photos/id/$index/200/200',
      placeholder: (context, url){
        return MyProgressIndicator();
      },
      imageBuilder: (BuildContext context, ImageProvider imageProvider) =>
          AspectRatio(
        aspectRatio: 1,
        child: Container(
          decoration: BoxDecoration(
              image: DecorationImage(image: imageProvider, fit: BoxFit.cover)),
        ),
      ),
    );
  }
}