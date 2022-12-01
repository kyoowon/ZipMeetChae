<template>
  <div id="intro-content">
    <div>
      <h3 class="intro-sub-title">Zip Meet Chae service</h3>
      <hr />
      <b-card-group deck>
        <b-card
          title="매물에 대한 정보를 습득"
          img-src="@/assets/img/intro-img3.png"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            매물에 대한 차트를 통해 변동의 추의를 확인하고, 지도를 통해 주변 입지를 확인하는 기능을
            제공
          </b-card-text>
        </b-card>

        <b-card
          title="온라인으로 매물을 확인"
          img-src="@/assets/img/intro-img4.png"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            게시판 형식의 다양한 매물에 대한 정보를 얻고 매매자와의 직접적인 연락을 할 수 있도록
            화상회의 (구글 밋)을 통해 제공
          </b-card-text>
        </b-card>

        <b-card title="직접 거래" img-src="@/assets/img/intro-img5.png" img-alt="Image" img-top>
          <b-card-text>
            이를 통해 허위 매물로 시간을 버리는 버리거나 바쁜 생활 속에서 시간에 내서 직접 매물을
            봐야하는 현대인에서 시간적 단축을 제공
          </b-card-text>
        </b-card>
      </b-card-group>
    </div>
    <h3 class="intro-sub-title">service developer</h3>
    <hr />
    <div class="blog-card">
      <div class="meta">
        <div
          class="photo"
          style="background-image: url(https://avatars.githubusercontent.com/u/50581881?v=4)"
        ></div>
        <ul class="details">
          <li class="author">Lee Kyu Won</li>
          <li class="date">Ocb. 13, 1995</li>
          <li class="tags">
            <ul>
              <li>Vue /</li>
              <li>Vuex /</li>
              <li>HTML/JS/CSS /</li>
              <li>SCSS/SASS</li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="description">
        <h1>UI & Frontend</h1>
        <h2>everything will come back to life on the screen</h2>
        <p>
          본 서비스에 UI 및 Frontend 개발을 맡아 진행한 이규원이라고 합니다. 짧은 시간 내에
          ZipMeetChae 서비스를 만드는 과정에 많은 어려움이 있었습니다. 그중에서도 비대면 서비스를
          (Zoom, google meet 등) 기존에 이용하던 서비스를 저희 서비스에서 제공하고자 하는 과정에서
          정말 많은 어려움이 있었습니다. 이러한 부분을 신경써서 개발에 진행했다는 점을 알아주셨으면
          좋겠습니다.
        </p>
        <p class="read-more">
          <a href="https://github.com/kyoowon">GitHub</a>
        </p>
      </div>
    </div>
    <div class="blog-card alt">
      <div class="meta">
        <div
          class="photo"
          style="
            background-image: url(img/minju.jpg);
          "
        ></div>
        <ul class="details">
          <li class="author">Yoon Min Ju</li>
          <li class="date">Nov. 20, 1997</li>
          <li class="tags">
            <ul>
              <li>Spring /</li>
              <li>MySql</li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="description">
        <h1>DB & Backend</h1>
        <h2>Java is not the same as JavaScript</h2>
        <p>
          ZipMeetChae 서비스의 Backend 개발과 DB를 맡은 윤민주입니다. spring boot 기반의 웹 서비스를
          개발하면서 Rest API와 JWT token, oauth에 대해 좀 더 이해하고 성장할 수 있는 경험이
          되었습니다.
        </p>
        <p class="read-more">
          <a href="https://github.com/ymj3539">GitHub</a>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.card {
  margin-bottom: 50px;
  border-radius: 10px;
  scale: 0.9;
  &:hover {
    scale: 1;
  }
}

.intro-sub-title {
  font-family: 'Rubik Bubbles', cursive;
  color: #f0a663;
  margin: 30px 0 0 0;
}

#intro-content {
  text-align: left;
  margin: 50px 0;
}

$color_white: #fff;
$color_prime: #5ad67d;
$color_grey: #e2e2e2;
$color_grey_dark: #a2a2a2;

.blog-card {
  display: flex;
  flex-direction: column;
  margin: 1rem auto;
  box-shadow: 0 3px 7px -1px rgba(#000, 0.1);
  margin-bottom: 1.6%;
  background: $color_white;
  line-height: 1.4;
  font-family: 'NanumSquareExtraBold';
  border-radius: 5px;
  overflow: hidden;
  z-index: 0;
  a {
    color: inherit;
    &:hover {
      color: $color_prime;
    }
  }
  &:hover {
    .photo {
      transform: scale(1.3) rotate(3deg);
    }
  }
  .meta {
    position: relative;
    z-index: 0;
    height: 200px;
  }
  .photo {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-size: cover;
    background-position: center;
    transition: transform 0.2s;
  }
  .details,
  .details ul {
    margin: auto;
    padding: 0;
    list-style: none;
  }

  .details {
    position: absolute;
    top: 0;
    bottom: 0;
    left: -100%;
    margin: auto;
    transition: left 0.2s;
    background: rgba(#000, 0.6);
    color: $color_white;
    padding: 10px;
    width: 100%;
    font-size: 0.9rem;
    a {
      text-decoration: dotted underline;
    }
    ul li {
      display: inline-block;
    }
    .author:before {
      font-family: 'NanumSquareLight';
      margin-right: 10px;
      // content: "\f007";
    }

    .date:before {
      font-family: 'NanumSquareLight';
      margin-right: 10px;
      // content: "\f133";
    }

    .tags {
      ul:before {
        font-family: 'NanumSquareLight';
        // content: "\f02b";
        margin-right: 10px;
      }
      li {
        margin-right: 2px;
        &:first-child {
          margin-left: -4px;
        }
      }
    }
  }
  .description {
    padding: 1rem;
    background: $color_white;
    position: relative;
    z-index: 1;
    h1,
    h2 {
      font-family: 'NanumSquareExtraBold';
    }
    h1 {
      line-height: 1;
      margin: 0;
      font-size: 1.7rem;
    }
    h2 {
      font-size: 1rem;
      font-weight: 300;
      text-transform: uppercase;
      color: $color_grey_dark;
      margin-top: 5px;
    }
    .read-more {
      text-align: right;
      a {
        color: $color_prime;
        display: inline-block;
        position: relative;
        &:after {
          // content: "\f061";
          font-family: 'NanumSquareLight';
          margin-left: -10px;
          opacity: 0;
          vertical-align: middle;
          transition: margin 0.3s, opacity 0.3s;
        }

        &:hover:after {
          margin-left: 5px;
          opacity: 1;
        }
      }
    }
  }
  p {
    position: relative;
    margin: 1rem 0 0;
    &:first-of-type {
      margin-top: 1.25rem;
      &:before {
        content: '';
        position: absolute;
        height: 5px;
        background: $color_prime;
        width: 35px;
        top: -0.75rem;
        border-radius: 3px;
      }
    }
  }
  &:hover {
    .details {
      left: 0%;
    }
  }

  @media (min-width: 640px) {
    flex-direction: row;
    max-width: 100%;
    .meta {
      flex-basis: 40%;
      height: auto;
    }
    .description {
      flex-basis: 60%;
      &:before {
        transform: skewX(-3deg);
        content: '';
        background: #fff;
        width: 30px;
        position: absolute;
        left: -10px;
        top: 0;
        bottom: 0;
        z-index: -1;
      }
    }
    &.alt {
      flex-direction: row-reverse;
      .description {
        &:before {
          left: inherit;
          right: -10px;
          transform: skew(3deg);
        }
      }
      .details {
        padding-left: 25px;
      }
    }
  }
}
</style>
