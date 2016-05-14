# ImageLoader

ImageLoader is an library for the [Processing](http://processing.org/) Development Environment (PDE).

ImageLoader is an simple to use API to load images from either Instagram, Flickr, Google, Giphy, Tumblr or your file system.

The API uses thread based loader task to fetch the images. It's possible to set an delay so that the task will run several times in the background and checks for new images. All images are stored in a list and can be accessed by several methods.

Keep in mind that GIFs can consume quite a lot of memory. So you might want to use the lazy load mode and clear the memory after some time. To decode the GIF files the Loader uses the [gifAnimation](https://github.com/extrapixel/gif-animation/tree/124fb806672dca50c9da954c2abffa4bff5ac3bb) library.

## Example (Flickr)

```java
ImageLoader loader;
ImageList list;
Image img;

void setup() {
  size(800, 450);

  loader = new FlickrLoader(this, apiKey, apiSecret);
  list = loader.start("sunset beach", false, 60 * 1000);
}

void draw() {
  if (img == null) {
    img = list.getRandom();
  } else {
    image(img.getImg(), 0, 0, width, height);
  }
}

void mousePressed() {
  img = list.getRandom();
}
```

## Example (Giphy)

```java
GifLoader loader;
GifList list;
PImage[] imgs;
int index;

void setup() {
  size(800, 450);
  frameRate(25);

  loader = new GiphyLoader(this, apiKey);
  list = loader.start("cat funny", false, 60 * 1000);

  index = 0;
}

void draw() {
  if (imgs == null) {
    if (list.size() > 0) {
      imgs = list.getRandom().getGifFrames();
    }
  } else {
    image(imgs[index], 0, 0, width, height);
    index++;
    if (index > imgs.length - 1) {
      index = 0;
    }
  }
}

void mousePressed() {
  imgs = list.getRandom().getGifFrames();
  index = 0;
}
```

## API

To connect to the APIs from Flickr, Instagram, Tumblr, Giphy and Google the ImageLoader uses other libraries.

* Instagram ([jInstagram] (https://github.com/sachin-handiekar/jInstagram))
* Google ([API Client Library for Java] (https://developers.google.com/api-client-library/java/))
* Flickr ([Flickr4Java] (https://github.com/callmeal/Flickr4Java))
* Giphy ([Giphy4J] (https://github.com/keshrath/Giphy4J))
* Tumblr ([Jumblr] (https://github.com/tumblr/jumblr))

### Instagram

First you need to register your application on the [Instagram developers page] (https://www.instagram.com/developer/).
Afterwards you get your client id and some other information. Every new client application starts in sandbox mode and therefore has some [restrictions] (https://www.instagram.com/developer/limits/). 

#### Client ID

```java
ImageLoader loader = new InstagramLoader(this, clientId);
```

Be aware that the clientId wont work as long as you are in sandbox mode.

#### Access Token

```java
ImageLoader loader = new InstagramLoader(this, accessToken, "");
```

To generate your access token, just follow these few steps:

* Register a new client application on Instagram.
* Fill out the required fields for registering a new client id making sure to set the OAuth redirect_uri field to "http://localhost" (without quotes).
* Also, be sure to uncheck the "Disable implicit OAuth" box or else this method will not work.
* Paste the following URL into any browser.</br>(https://instagram.com/oauth/authorize/?client_id=[CLIENT_ID_HERE]&redirect_uri=http://localhost&response_type=token&scope=public)
* Make sure that you replaced the [CLIENT_ID_HERE] with your generated one.
* Accept the access for your client.
* Now Instagram will redirect you to your local host and you can copy the token from the URL. (localhost/#access_token=...)

### Flickr

Get your Flickr API key and API secret by following the steps on the [Flickr developers page] (https://www.flickr.com/services/developer/api/).

```java
ImageLoader loader = new FlickrLoader(this, apiKey, apiSecret);
```

### Google

Get your Google API key by following the steps on the [Google developers page] (https://console.developers.google.com/start).

```java
ImageLoader loader = new GoogleLoader(this, apiKey);
```

### Giphy

Get your Giphy API key from the [Giphy Homepage] (https://api.giphy.com/).

```java
GifLoader loader = new GiphyLoader(this, apiKey);
```

### Tumblr

Get your Tumblr API key from the [Tumblr Homepage] (https://www.tumblr.com/docs/en/api/v2).

```java
TublrImageLoader loader = new TublrImageLoader(this, apiKey, apiSecret);
```

## How to install

Download ImageLoader library from [here](https://github.com/keshrath/ImageLoader/blob/master/distribution/ImageLoader/download/ImageLoader.zip?raw=true).

Unzip and copy it into the `libraries` folder in the Processing sketchbook. You will need to create this `libraries` folder if it does not exist.

To find (and change) the Processing sketchbook location on your computer, open the Preferences window from the Processing application (PDE) and look for the "Sketchbook location" item at the top.

By default the following locations are used for your sketchbook folder: 
  * For Mac users, the sketchbook folder is located inside `~/Documents/Processing` 
  * For Windows users, the sketchbook folder is located inside `My Documents/Processing`

The folder structure for library Console should be as follows:

```
Processing
  libraries
    ImageLoader
      examples
      library
      reference
      src
      library.properties
```