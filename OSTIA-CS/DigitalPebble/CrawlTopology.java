



<!DOCTYPE html>
<html lang="en" class=" is-copy-enabled">
  <head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# object: http://ogp.me/ns/object# article: http://ogp.me/ns/article# profile: http://ogp.me/ns/profile#">
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta name="viewport" content="width=1020">
    
    
    <title>storm-crawler/CrawlTopology.java at master · DigitalPebble/storm-crawler</title>
    <link rel="search" type="application/opensearchdescription+xml" href="/opensearch.xml" title="GitHub">
    <link rel="fluid-icon" href="https://github.com/fluidicon.png" title="GitHub">
    <link rel="apple-touch-icon" sizes="57x57" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-144.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144.png">
    <meta property="fb:app_id" content="1401488693436528">

      <meta content="@github" name="twitter:site" /><meta content="summary" name="twitter:card" /><meta content="DigitalPebble/storm-crawler" name="twitter:title" /><meta content="storm-crawler - Web crawler SDK based on Apache Storm " name="twitter:description" /><meta content="https://avatars3.githubusercontent.com/u/1726647?v=3&amp;s=400" name="twitter:image:src" />
      <meta content="GitHub" property="og:site_name" /><meta content="object" property="og:type" /><meta content="https://avatars3.githubusercontent.com/u/1726647?v=3&amp;s=400" property="og:image" /><meta content="DigitalPebble/storm-crawler" property="og:title" /><meta content="https://github.com/DigitalPebble/storm-crawler" property="og:url" /><meta content="storm-crawler - Web crawler SDK based on Apache Storm " property="og:description" />
      <meta name="browser-stats-url" content="https://api.github.com/_private/browser/stats">
    <meta name="browser-errors-url" content="https://api.github.com/_private/browser/errors">
    <link rel="assets" href="https://assets-cdn.github.com/">
    <link rel="web-socket" href="wss://live.github.com/_sockets/MTg5NTQxNTphZWIyYjEzOWFjYjlhZWRhNGY3NGRiZTQwZDM0ZDI1NjphYTg0NTA4MTRiYWE5NDZmNDc4NmFkZjNjYzc2NjU3ZTk1Y2Y2ZDAxNjBmZTZmYTNkZTdkMTM2NjIyZWY2OTIw--fb51eacb032064557e5397bb9e981ddecf9c05e2">
    <meta name="pjax-timeout" content="1000">
    <link rel="sudo-modal" href="/sessions/sudo_modal">

    <meta name="msapplication-TileImage" content="/windows-tile.png">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="selected-link" value="repo_source" data-pjax-transient>

    <meta name="google-site-verification" content="KT5gs8h0wvaagLKAVWq8bbeNwnZZK1r1XQysX3xurLU">
    <meta name="google-analytics" content="UA-3769691-2">

<meta content="collector.githubapp.com" name="octolytics-host" /><meta content="github" name="octolytics-app-id" /><meta content="83AF8741:377E:6E278F:569663D4" name="octolytics-dimension-request_id" /><meta content="1895415" name="octolytics-actor-id" /><meta content="maelstromdat" name="octolytics-actor-login" /><meta content="3d0a0b53dd07d8dcf958ef24d93aee0bb2ab8275e992c636b3eafcad6fd6afdc" name="octolytics-actor-hash" />
<meta content="/&lt;user-name&gt;/&lt;repo-name&gt;/blob/show" data-pjax-transient="true" name="analytics-location" />
<meta content="Rails, view, blob#show" data-pjax-transient="true" name="analytics-event" />


  <meta class="js-ga-set" name="dimension1" content="Logged In">



        <meta name="hostname" content="github.com">
    <meta name="user-login" content="maelstromdat">

        <meta name="expected-hostname" content="github.com">

      <link rel="mask-icon" href="https://assets-cdn.github.com/pinned-octocat.svg" color="#4078c0">
      <link rel="icon" type="image/x-icon" href="https://assets-cdn.github.com/favicon.ico">

    <meta content="1a0e8656aaf3cc467a9f436f0770e121e3175993" name="form-nonce" />

    <link crossorigin="anonymous" href="https://assets-cdn.github.com/assets/github-3650b46c23630852a7922fb76748456158cce211f4912993f60326d1c47ce49c.css" integrity="sha256-NlC0bCNjCFKnki+3Z0hFYVjM4hH0kSmT9gMm0cR85Jw=" media="all" rel="stylesheet" />
    <link crossorigin="anonymous" href="https://assets-cdn.github.com/assets/github2-c11005a6d45ee1ef4e164093521773ed34c29ed1f11e2faa2708488033b57391.css" integrity="sha256-wRAFptRe4e9OFkCTUhdz7TTCntHxHi+qJwhIgDO1c5E=" media="all" rel="stylesheet" />
    
    


    <meta http-equiv="x-pjax-version" content="69e1d814c435ab83cfb2ca391f13cbef">

      
  <meta name="description" content="storm-crawler - Web crawler SDK based on Apache Storm ">
  <meta name="go-import" content="github.com/DigitalPebble/storm-crawler git https://github.com/DigitalPebble/storm-crawler.git">

  <meta content="1726647" name="octolytics-dimension-user_id" /><meta content="DigitalPebble" name="octolytics-dimension-user_login" /><meta content="9395761" name="octolytics-dimension-repository_id" /><meta content="DigitalPebble/storm-crawler" name="octolytics-dimension-repository_nwo" /><meta content="true" name="octolytics-dimension-repository_public" /><meta content="false" name="octolytics-dimension-repository_is_fork" /><meta content="9395761" name="octolytics-dimension-repository_network_root_id" /><meta content="DigitalPebble/storm-crawler" name="octolytics-dimension-repository_network_root_nwo" />
  <link href="https://github.com/DigitalPebble/storm-crawler/commits/master.atom" rel="alternate" title="Recent Commits to storm-crawler:master" type="application/atom+xml">

  </head>


  <body class="logged_in   env-production macintosh vis-public page-blob">
    <a href="#start-of-content" tabindex="1" class="accessibility-aid js-skip-to-content">Skip to content</a>

    
    
    



      <div class="header header-logged-in true" role="banner">
  <div class="container clearfix">

    <a class="header-logo-invertocat" href="https://github.com/" data-hotkey="g d" aria-label="Homepage" data-ga-click="Header, go to dashboard, icon:logo">
  <span aria-hidden="true" class="mega-octicon octicon-mark-github"></span>
</a>


      <div class="site-search repo-scope js-site-search" role="search">
          <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/DigitalPebble/storm-crawler/search" class="js-site-search-form" data-global-search-url="/search" data-repo-search-url="/DigitalPebble/storm-crawler/search" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
  <label class="js-chromeless-input-container form-control">
    <div class="scope-badge">This repository</div>
    <input type="text"
      class="js-site-search-focus js-site-search-field is-clearable chromeless-input"
      data-hotkey="s"
      name="q"
      placeholder="Search"
      aria-label="Search this repository"
      data-global-scope-placeholder="Search GitHub"
      data-repo-scope-placeholder="Search"
      tabindex="1"
      autocapitalize="off">
  </label>
</form>
      </div>

      <ul class="header-nav left" role="navigation">
        <li class="header-nav-item">
          <a href="/pulls" class="js-selected-navigation-item header-nav-link" data-ga-click="Header, click, Nav menu - item:pulls context:user" data-hotkey="g p" data-selected-links="/pulls /pulls/assigned /pulls/mentioned /pulls">
            Pull requests
</a>        </li>
        <li class="header-nav-item">
          <a href="/issues" class="js-selected-navigation-item header-nav-link" data-ga-click="Header, click, Nav menu - item:issues context:user" data-hotkey="g i" data-selected-links="/issues /issues/assigned /issues/mentioned /issues">
            Issues
</a>        </li>
          <li class="header-nav-item">
            <a class="header-nav-link" href="https://gist.github.com/" data-ga-click="Header, go to gist, text:gist">Gist</a>
          </li>
      </ul>

    
<ul class="header-nav user-nav right" id="user-links">
  <li class="header-nav-item">
      <span class="js-socket-channel js-updatable-content"
        data-channel="notification-changed:maelstromdat"
        data-url="/notifications/header">
      <a href="/notifications" aria-label="You have no unread notifications" class="header-nav-link notification-indicator tooltipped tooltipped-s" data-ga-click="Header, go to notifications, icon:read" data-hotkey="g n">
          <span class="mail-status all-read"></span>
          <span aria-hidden="true" class="octicon octicon-bell"></span>
</a>  </span>

  </li>

  <li class="header-nav-item dropdown js-menu-container">
    <a class="header-nav-link tooltipped tooltipped-s js-menu-target" href="/new"
       aria-label="Create new…"
       data-ga-click="Header, create new, icon:add">
      <span aria-hidden="true" class="octicon octicon-plus left"></span>
      <span class="dropdown-caret"></span>
    </a>

    <div class="dropdown-menu-content js-menu-content">
      <ul class="dropdown-menu dropdown-menu-sw">
        
<a class="dropdown-item" href="/new" data-ga-click="Header, create new repository">
  New repository
</a>


  <a class="dropdown-item" href="/organizations/new" data-ga-click="Header, create new organization">
    New organization
  </a>



  <div class="dropdown-divider"></div>
  <div class="dropdown-header">
    <span title="DigitalPebble/storm-crawler">This repository</span>
  </div>
    <a class="dropdown-item" href="/DigitalPebble/storm-crawler/issues/new" data-ga-click="Header, create new issue">
      New issue
    </a>

      </ul>
    </div>
  </li>

  <li class="header-nav-item dropdown js-menu-container">
    <a class="header-nav-link name tooltipped tooltipped-sw js-menu-target" href="/maelstromdat"
       aria-label="View profile and more"
       data-ga-click="Header, show menu, icon:avatar">
      <img alt="@maelstromdat" class="avatar" height="20" src="https://avatars1.githubusercontent.com/u/1895415?v=3&amp;s=40" width="20" />
      <span class="dropdown-caret"></span>
    </a>

    <div class="dropdown-menu-content js-menu-content">
      <div class="dropdown-menu  dropdown-menu-sw">
        <div class=" dropdown-header header-nav-current-user css-truncate">
            Signed in as <strong class="css-truncate-target">maelstromdat</strong>

        </div>


        <div class="dropdown-divider"></div>

          <a class="dropdown-item" href="/maelstromdat" data-ga-click="Header, go to profile, text:your profile">
            Your profile
          </a>
        <a class="dropdown-item" href="/stars" data-ga-click="Header, go to starred repos, text:your stars">
          Your stars
        </a>
        <a class="dropdown-item" href="/explore" data-ga-click="Header, go to explore, text:explore">
          Explore
        </a>
          <a class="dropdown-item" href="/integrations" data-ga-click="Header, go to integrations, text:integrations">
            Integrations
          </a>
        <a class="dropdown-item" href="https://help.github.com" data-ga-click="Header, go to help, text:help">
          Help
        </a>

          <div class="dropdown-divider"></div>

          <a class="dropdown-item" href="/settings/profile" data-ga-click="Header, go to settings, icon:settings">
            Settings
          </a>

          <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/logout" class="logout-form" data-form-nonce="1a0e8656aaf3cc467a9f436f0770e121e3175993" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="CKTSgkwf9TIZeU6xMuD+LcWtHYkHghOhPcVDx/v1msD/n5ZS5rZYo3zaag4javc3HXr6SwE0h0/YnoIuVyacqg==" /></div>
            <button class="dropdown-item dropdown-signout" data-ga-click="Header, sign out, icon:logout">
              Sign out
            </button>
</form>
      </div>
    </div>
  </li>
</ul>


    
  </div>
</div>

      

      


    <div id="start-of-content" class="accessibility-aid"></div>

      <div id="js-flash-container">
</div>


    <div role="main" class="main-content">
        <div itemscope itemtype="http://schema.org/WebPage">
    <div id="js-repo-pjax-container" class="context-loader-container js-repo-nav-next" data-pjax-container>
      
<div class="pagehead repohead instapaper_ignore readability-menu experiment-repo-nav">
  <div class="container repohead-details-container">

    

<ul class="pagehead-actions">

  <li>
        <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/notifications/subscribe" class="js-social-container" data-autosubmit="true" data-form-nonce="1a0e8656aaf3cc467a9f436f0770e121e3175993" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="Z/3/ovArr0nDIf8t5fxGjN72/XSVxtt5X1Jc+3S1yYGty1cTq0LroPN/mI/JJ7oyL0CzXakZpHQCWxouoX13RA==" /></div>      <input id="repository_id" name="repository_id" type="hidden" value="9395761" />

        <div class="select-menu js-menu-container js-select-menu">
          <a href="/DigitalPebble/storm-crawler/subscription"
            class="btn btn-sm btn-with-count select-menu-button js-menu-target" role="button" tabindex="0" aria-haspopup="true"
            data-ga-click="Repository, click Watch settings, action:blob#show">
            <span class="js-select-button">
              <span aria-hidden="true" class="octicon octicon-eye"></span>
              Watch
            </span>
          </a>
          <a class="social-count js-social-count" href="/DigitalPebble/storm-crawler/watchers">
            26
          </a>

        <div class="select-menu-modal-holder">
          <div class="select-menu-modal subscription-menu-modal js-menu-content" aria-hidden="true">
            <div class="select-menu-header">
              <span aria-label="Close" class="octicon octicon-x js-menu-close" role="button"></span>
              <span class="select-menu-title">Notifications</span>
            </div>

              <div class="select-menu-list js-navigation-container" role="menu">

                <div class="select-menu-item js-navigation-item selected" role="menuitem" tabindex="0">
                  <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
                  <div class="select-menu-item-text">
                    <input checked="checked" id="do_included" name="do" type="radio" value="included" />
                    <span class="select-menu-item-heading">Not watching</span>
                    <span class="description">Be notified when participating or @mentioned.</span>
                    <span class="js-select-button-text hidden-select-button-text">
                      <span aria-hidden="true" class="octicon octicon-eye"></span>
                      Watch
                    </span>
                  </div>
                </div>

                <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
                  <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
                  <div class="select-menu-item-text">
                    <input id="do_subscribed" name="do" type="radio" value="subscribed" />
                    <span class="select-menu-item-heading">Watching</span>
                    <span class="description">Be notified of all conversations.</span>
                    <span class="js-select-button-text hidden-select-button-text">
                      <span aria-hidden="true" class="octicon octicon-eye"></span>
                      Unwatch
                    </span>
                  </div>
                </div>

                <div class="select-menu-item js-navigation-item " role="menuitem" tabindex="0">
                  <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
                  <div class="select-menu-item-text">
                    <input id="do_ignore" name="do" type="radio" value="ignore" />
                    <span class="select-menu-item-heading">Ignoring</span>
                    <span class="description">Never be notified.</span>
                    <span class="js-select-button-text hidden-select-button-text">
                      <span aria-hidden="true" class="octicon octicon-mute"></span>
                      Stop ignoring
                    </span>
                  </div>
                </div>

              </div>

            </div>
          </div>
        </div>
</form>
  </li>

  <li>
    
  <div class="js-toggler-container js-social-container starring-container ">

    <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/DigitalPebble/storm-crawler/unstar" class="js-toggler-form starred js-unstar-button" data-form-nonce="1a0e8656aaf3cc467a9f436f0770e121e3175993" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="LXMERPXxiza1pMifgajywxmI+FInQ6mcN/v8P3O+ixa0rgv6yd8v+AZ62DF1xtp36gSdirX4YG7KcttvbQ8z5Q==" /></div>
      <button
        class="btn btn-sm btn-with-count js-toggler-target"
        aria-label="Unstar this repository" title="Unstar DigitalPebble/storm-crawler"
        data-ga-click="Repository, click unstar button, action:blob#show; text:Unstar">
        <span aria-hidden="true" class="octicon octicon-star"></span>
        Unstar
      </button>
        <a class="social-count js-social-count" href="/DigitalPebble/storm-crawler/stargazers">
          123
        </a>
</form>
    <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/DigitalPebble/storm-crawler/star" class="js-toggler-form unstarred js-star-button" data-form-nonce="1a0e8656aaf3cc467a9f436f0770e121e3175993" data-remote="true" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="0b/QrZWG2pjYg1JAZz4R7wvOMIDh+xJJF8Zf8IIPSK6KtnGuvmfoMCmJSgyzLMX0rAzkV66VyVogdGe1ecy45Q==" /></div>
      <button
        class="btn btn-sm btn-with-count js-toggler-target"
        aria-label="Star this repository" title="Star DigitalPebble/storm-crawler"
        data-ga-click="Repository, click star button, action:blob#show; text:Star">
        <span aria-hidden="true" class="octicon octicon-star"></span>
        Star
      </button>
        <a class="social-count js-social-count" href="/DigitalPebble/storm-crawler/stargazers">
          123
        </a>
</form>  </div>

  </li>

  <li>
          <a href="#fork-destination-box" class="btn btn-sm btn-with-count"
              title="Fork your own copy of DigitalPebble/storm-crawler to your account"
              aria-label="Fork your own copy of DigitalPebble/storm-crawler to your account"
              rel="facebox"
              data-ga-click="Repository, show fork modal, action:blob#show; text:Fork">
              <span aria-hidden="true" class="octicon octicon-repo-forked"></span>
            Fork
          </a>

          <div id="fork-destination-box" style="display: none;">
            <h2 class="facebox-header" data-facebox-id="facebox-header">Where should we fork this repository?</h2>
            <include-fragment src=""
                class="js-fork-select-fragment fork-select-fragment"
                data-url="/DigitalPebble/storm-crawler/fork?fragment=1">
              <img alt="Loading" height="64" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-128.gif" width="64" />
            </include-fragment>
          </div>

    <a href="/DigitalPebble/storm-crawler/network" class="social-count">
      48
    </a>
  </li>
</ul>

    <h1 itemscope itemtype="http://data-vocabulary.org/Breadcrumb" class="entry-title public ">
  <span aria-hidden="true" class="octicon octicon-repo"></span>
  <span class="author"><a href="/DigitalPebble" class="url fn" itemprop="url" rel="author"><span itemprop="title">DigitalPebble</span></a></span><!--
--><span class="path-divider">/</span><!--
--><strong><a href="/DigitalPebble/storm-crawler" data-pjax="#js-repo-pjax-container">storm-crawler</a></strong>

  <span class="page-context-loader">
    <img alt="" height="16" src="https://assets-cdn.github.com/images/spinners/octocat-spinner-32.gif" width="16" />
  </span>

</h1>

  </div>
  <div class="container">
    
<nav class="reponav js-repo-nav js-sidenav-container-pjax js-octicon-loaders"
     role="navigation"
     data-pjax="#js-repo-pjax-container">

  <a href="/DigitalPebble/storm-crawler" aria-label="Code" aria-selected="true" class="js-selected-navigation-item selected reponav-item" data-hotkey="g c" data-selected-links="repo_source repo_downloads repo_commits repo_releases repo_tags repo_branches /DigitalPebble/storm-crawler">
    <span aria-hidden="true" class="octicon octicon-code"></span>
    Code
</a>
    <a href="/DigitalPebble/storm-crawler/issues" class="js-selected-navigation-item reponav-item" data-hotkey="g i" data-selected-links="repo_issues repo_labels repo_milestones /DigitalPebble/storm-crawler/issues">
      <span aria-hidden="true" class="octicon octicon-issue-opened"></span>
      Issues
      <span class="counter">33</span>
</a>
  <a href="/DigitalPebble/storm-crawler/pulls" class="js-selected-navigation-item reponav-item" data-hotkey="g p" data-selected-links="repo_pulls /DigitalPebble/storm-crawler/pulls">
    <span aria-hidden="true" class="octicon octicon-git-pull-request"></span>
    Pull requests
    <span class="counter">2</span>
</a>
    <a href="/DigitalPebble/storm-crawler/wiki" class="js-selected-navigation-item reponav-item" data-hotkey="g w" data-selected-links="repo_wiki /DigitalPebble/storm-crawler/wiki">
      <span aria-hidden="true" class="octicon octicon-book"></span>
      Wiki
</a>
  <a href="/DigitalPebble/storm-crawler/pulse" class="js-selected-navigation-item reponav-item" data-selected-links="pulse /DigitalPebble/storm-crawler/pulse">
    <span aria-hidden="true" class="octicon octicon-pulse"></span>
    Pulse
</a>
  <a href="/DigitalPebble/storm-crawler/graphs" class="js-selected-navigation-item reponav-item" data-selected-links="repo_graphs repo_contributors /DigitalPebble/storm-crawler/graphs">
    <span aria-hidden="true" class="octicon octicon-graph"></span>
    Graphs
</a>

</nav>

  </div>
</div>

<div class="container new-discussion-timeline experiment-repo-nav">
  <div class="repository-content">

    

<a href="/DigitalPebble/storm-crawler/blob/ebdc68a343ae74f93e30d2d0bd73ddced12c2923/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java" class="hidden js-permalink-shortcut" data-hotkey="y">Permalink</a>

<!-- blob contrib key: blob_contributors:v21:6b5d9305658fc26d35406569fdf8b800 -->

<div class="file-navigation js-zeroclipboard-container">
  
<div class="select-menu js-menu-container js-select-menu left">
  <button class="btn btn-sm select-menu-button js-menu-target css-truncate" data-hotkey="w"
    title="master"
    type="button" aria-label="Switch branches or tags" tabindex="0" aria-haspopup="true">
    <i>Branch:</i>
    <span class="js-select-button css-truncate-target">master</span>
  </button>

  <div class="select-menu-modal-holder js-menu-content js-navigation-container" data-pjax aria-hidden="true">

    <div class="select-menu-modal">
      <div class="select-menu-header">
        <span aria-label="Close" class="octicon octicon-x js-menu-close" role="button"></span>
        <span class="select-menu-title">Switch branches/tags</span>
      </div>

      <div class="select-menu-filters">
        <div class="select-menu-text-filter">
          <input type="text" aria-label="Filter branches/tags" id="context-commitish-filter-field" class="js-filterable-field js-navigation-enable" placeholder="Filter branches/tags">
        </div>
        <div class="select-menu-tabs">
          <ul>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="branches" data-filter-placeholder="Filter branches/tags" class="js-select-menu-tab" role="tab">Branches</a>
            </li>
            <li class="select-menu-tab">
              <a href="#" data-tab-filter="tags" data-filter-placeholder="Find a tag…" class="js-select-menu-tab" role="tab">Tags</a>
            </li>
          </ul>
        </div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="branches" role="menu">

        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/DigitalPebble/storm-crawler/blob/164/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
               data-name="164"
               data-skip-pjax="true"
               rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="164">
                164
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/DigitalPebble/storm-crawler/blob/feature/non-blocking-fetcher/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
               data-name="feature/non-blocking-fetcher"
               data-skip-pjax="true"
               rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="feature/non-blocking-fetcher">
                feature/non-blocking-fetcher
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/DigitalPebble/storm-crawler/blob/gh-pages/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
               data-name="gh-pages"
               data-skip-pjax="true"
               rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="gh-pages">
                gh-pages
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open selected"
               href="/DigitalPebble/storm-crawler/blob/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
               data-name="master"
               data-skip-pjax="true"
               rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="master">
                master
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/DigitalPebble/storm-crawler/blob/maven-shade/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
               data-name="maven-shade"
               data-skip-pjax="true"
               rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="maven-shade">
                maven-shade
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/DigitalPebble/storm-crawler/blob/sharding/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
               data-name="sharding"
               data-skip-pjax="true"
               rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="sharding">
                sharding
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
               href="/DigitalPebble/storm-crawler/blob/storm-0.10/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
               data-name="storm-0.10"
               data-skip-pjax="true"
               rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="storm-0.10">
                storm-0.10
              </span>
            </a>
        </div>

          <div class="select-menu-no-results">Nothing to show</div>
      </div>

      <div class="select-menu-list select-menu-tab-bucket js-select-menu-tab-bucket" data-tab-filter="tags">
        <div data-filterable-for="context-commitish-filter-field" data-filterable-type="substring">


            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.8/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.8"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.8">
                0.8
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.7/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.7"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.7">
                0.7
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.6/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.6"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.6">
                0.6
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.5/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.5"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.5">
                0.5
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.4/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.4"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.4">
                0.4
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.3/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.3"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.3">
                0.3
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.2/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.2"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.2">
                0.2
              </span>
            </a>
            <a class="select-menu-item js-navigation-item js-navigation-open "
              href="/DigitalPebble/storm-crawler/tree/0.1/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java"
              data-name="0.1"
              data-skip-pjax="true"
              rel="nofollow">
              <span aria-hidden="true" class="octicon octicon-check select-menu-item-icon"></span>
              <span class="select-menu-item-text css-truncate-target" title="0.1">
                0.1
              </span>
            </a>
        </div>

        <div class="select-menu-no-results">Nothing to show</div>
      </div>

    </div>
  </div>
</div>

  <div class="btn-group right">
    <a href="/DigitalPebble/storm-crawler/find/master"
          class="js-show-file-finder btn btn-sm"
          data-pjax
          data-hotkey="t">
      Find file
    </a>
    <button aria-label="Copy file path to clipboard" class="js-zeroclipboard btn btn-sm zeroclipboard-button tooltipped tooltipped-s" data-copied-hint="Copied!" type="button">Copy path</button>
  </div>
  <div class="breadcrumb js-zeroclipboard-target">
    <span class="repo-root js-repo-root"><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">storm-crawler</span></a></span></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">core</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core/src" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">src</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core/src/main" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">main</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core/src/main/java" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">java</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core/src/main/java/com" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">com</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core/src/main/java/com/digitalpebble" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">digitalpebble</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core/src/main/java/com/digitalpebble/storm" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">storm</span></a></span><span class="separator">/</span><span itemscope="" itemtype="http://data-vocabulary.org/Breadcrumb"><a href="/DigitalPebble/storm-crawler/tree/master/core/src/main/java/com/digitalpebble/storm/crawler" class="" data-branch="master" data-pjax="true" itemscope="url"><span itemprop="title">crawler</span></a></span><span class="separator">/</span><strong class="final-path">CrawlTopology.java</strong>
  </div>
</div>


  <div class="commit-tease">
      <span class="right">
        <a class="commit-tease-sha" href="/DigitalPebble/storm-crawler/commit/5f4943d1746a145879f1c92f9e14cc7450b50564" data-pjax>
          5f4943d
        </a>
        <time datetime="2015-12-02T15:11:53Z" is="relative-time">Dec 2, 2015</time>
      </span>
      <div>
        <img alt="@jnioche" class="avatar" height="20" src="https://avatars0.githubusercontent.com/u/218319?v=3&amp;s=40" width="20" />
        <a href="/jnioche" class="user-mention" rel="contributor">jnioche</a>
          <a href="/DigitalPebble/storm-crawler/commit/5f4943d1746a145879f1c92f9e14cc7450b50564" class="message" data-pjax="true" title="Replaced RandomURLSpout with MemorySpout + added MemoryStatusUpdater">Replaced RandomURLSpout with MemorySpout + added MemoryStatusUpdater</a>
      </div>

    <div class="commit-tease-contributors">
      <a class="muted-link contributors-toggle" href="#blob_contributors_box" rel="facebox">
        <strong>2</strong>
         contributors
      </a>
          <a class="avatar-link tooltipped tooltipped-s" aria-label="jnioche" href="/DigitalPebble/storm-crawler/commits/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java?author=jnioche"><img alt="@jnioche" class="avatar" height="20" src="https://avatars0.githubusercontent.com/u/218319?v=3&amp;s=40" width="20" /> </a>
    <a class="avatar-link tooltipped tooltipped-s" aria-label="GuiForget" href="/DigitalPebble/storm-crawler/commits/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java?author=GuiForget"><img alt="@GuiForget" class="avatar" height="20" src="https://avatars3.githubusercontent.com/u/2585258?v=3&amp;s=40" width="20" /> </a>


    </div>

    <div id="blob_contributors_box" style="display:none">
      <h2 class="facebox-header" data-facebox-id="facebox-header">Users who have contributed to this file</h2>
      <ul class="facebox-user-list" data-facebox-id="facebox-description">
          <li class="facebox-user-list-item">
            <img alt="@jnioche" height="24" src="https://avatars2.githubusercontent.com/u/218319?v=3&amp;s=48" width="24" />
            <a href="/jnioche">jnioche</a>
          </li>
          <li class="facebox-user-list-item">
            <img alt="@GuiForget" height="24" src="https://avatars1.githubusercontent.com/u/2585258?v=3&amp;s=48" width="24" />
            <a href="/GuiForget">GuiForget</a>
          </li>
      </ul>
    </div>
  </div>

<div class="file">
  <div class="file-header">
  <div class="file-actions">

    <div class="btn-group">
      <a href="/DigitalPebble/storm-crawler/raw/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java" class="btn btn-sm " id="raw-url">Raw</a>
        <a href="/DigitalPebble/storm-crawler/blame/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java" class="btn btn-sm js-update-url-with-hash">Blame</a>
      <a href="/DigitalPebble/storm-crawler/commits/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java" class="btn btn-sm " rel="nofollow">History</a>
    </div>

        <a class="btn-octicon tooltipped tooltipped-nw"
           href="github-mac://openRepo/https://github.com/DigitalPebble/storm-crawler?branch=master&amp;filepath=core%2Fsrc%2Fmain%2Fjava%2Fcom%2Fdigitalpebble%2Fstorm%2Fcrawler%2FCrawlTopology.java"
           aria-label="Open this file in GitHub Desktop"
           data-ga-click="Repository, open with desktop, type:mac">
            <span aria-hidden="true" class="octicon octicon-device-desktop"></span>
        </a>

        <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/DigitalPebble/storm-crawler/edit/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java" class="inline-form js-update-url-with-hash" data-form-nonce="1a0e8656aaf3cc467a9f436f0770e121e3175993" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="MnfUJwoFXaQszgtxmemJxQh2GL1YC77XcplPEGMoA6YGjsYpny3vWFVVl55LHUhUzcVxoAr3UE2qUpf7Op+LVg==" /></div>
          <button class="btn-octicon tooltipped tooltipped-nw" type="submit"
            aria-label="Fork this project and edit the file" data-hotkey="e" data-disable-with>
            <span aria-hidden="true" class="octicon octicon-pencil"></span>
          </button>
</form>        <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="/DigitalPebble/storm-crawler/delete/master/core/src/main/java/com/digitalpebble/storm/crawler/CrawlTopology.java" class="inline-form" data-form-nonce="1a0e8656aaf3cc467a9f436f0770e121e3175993" method="post"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token" type="hidden" value="s+ohtmJ7/OF929WTwf0aMqndjPlVqAhTiywGl0XnxgLWIS5iVzHhjVbCFkiFaJm7c+rn/IiUjINVcVVUUOqcaA==" /></div>
          <button class="btn-octicon btn-octicon-danger tooltipped tooltipped-nw" type="submit"
            aria-label="Fork this project and delete the file" data-disable-with>
            <span aria-hidden="true" class="octicon octicon-trashcan"></span>
          </button>
</form>  </div>

  <div class="file-info">
      74 lines (58 sloc)
      <span class="file-info-divider"></span>
    2.88 KB
  </div>
</div>

  

  <div class="blob-wrapper data type-java">
      <table class="highlight tab-size js-file-line-container" data-tab-size="8">
      <tr>
        <td id="L1" class="blob-num js-line-number" data-line-number="1"></td>
        <td id="LC1" class="blob-code blob-code-inner js-file-line"><span class="pl-c">/**</span></td>
      </tr>
      <tr>
        <td id="L2" class="blob-num js-line-number" data-line-number="2"></td>
        <td id="LC2" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * Licensed to DigitalPebble Ltd under one or more</span></td>
      </tr>
      <tr>
        <td id="L3" class="blob-num js-line-number" data-line-number="3"></td>
        <td id="LC3" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * contributor license agreements.  See the NOTICE file distributed with</span></td>
      </tr>
      <tr>
        <td id="L4" class="blob-num js-line-number" data-line-number="4"></td>
        <td id="LC4" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * this work for additional information regarding copyright ownership.</span></td>
      </tr>
      <tr>
        <td id="L5" class="blob-num js-line-number" data-line-number="5"></td>
        <td id="LC5" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * DigitalPebble licenses this file to You under the Apache License, Version 2.0</span></td>
      </tr>
      <tr>
        <td id="L6" class="blob-num js-line-number" data-line-number="6"></td>
        <td id="LC6" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * (the &quot;License&quot;); you may not use this file except in compliance with</span></td>
      </tr>
      <tr>
        <td id="L7" class="blob-num js-line-number" data-line-number="7"></td>
        <td id="LC7" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * the License.  You may obtain a copy of the License at</span></td>
      </tr>
      <tr>
        <td id="L8" class="blob-num js-line-number" data-line-number="8"></td>
        <td id="LC8" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> *</span></td>
      </tr>
      <tr>
        <td id="L9" class="blob-num js-line-number" data-line-number="9"></td>
        <td id="LC9" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> *     http://www.apache.org/licenses/LICENSE-2.0</span></td>
      </tr>
      <tr>
        <td id="L10" class="blob-num js-line-number" data-line-number="10"></td>
        <td id="LC10" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> *</span></td>
      </tr>
      <tr>
        <td id="L11" class="blob-num js-line-number" data-line-number="11"></td>
        <td id="LC11" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * Unless required by applicable law or agreed to in writing, software</span></td>
      </tr>
      <tr>
        <td id="L12" class="blob-num js-line-number" data-line-number="12"></td>
        <td id="LC12" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,</span></td>
      </tr>
      <tr>
        <td id="L13" class="blob-num js-line-number" data-line-number="13"></td>
        <td id="LC13" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.</span></td>
      </tr>
      <tr>
        <td id="L14" class="blob-num js-line-number" data-line-number="14"></td>
        <td id="LC14" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * See the License for the specific language governing permissions and</span></td>
      </tr>
      <tr>
        <td id="L15" class="blob-num js-line-number" data-line-number="15"></td>
        <td id="LC15" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * limitations under the License.</span></td>
      </tr>
      <tr>
        <td id="L16" class="blob-num js-line-number" data-line-number="16"></td>
        <td id="LC16" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> */</span></td>
      </tr>
      <tr>
        <td id="L17" class="blob-num js-line-number" data-line-number="17"></td>
        <td id="LC17" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L18" class="blob-num js-line-number" data-line-number="18"></td>
        <td id="LC18" class="blob-code blob-code-inner js-file-line"><span class="pl-k">package</span> <span class="pl-smi">com.digitalpebble.storm.crawler</span>;</td>
      </tr>
      <tr>
        <td id="L19" class="blob-num js-line-number" data-line-number="19"></td>
        <td id="LC19" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L20" class="blob-num js-line-number" data-line-number="20"></td>
        <td id="LC20" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">com.digitalpebble.storm.crawler.bolt.FetcherBolt</span>;</td>
      </tr>
      <tr>
        <td id="L21" class="blob-num js-line-number" data-line-number="21"></td>
        <td id="LC21" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">com.digitalpebble.storm.crawler.bolt.JSoupParserBolt</span>;</td>
      </tr>
      <tr>
        <td id="L22" class="blob-num js-line-number" data-line-number="22"></td>
        <td id="LC22" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">com.digitalpebble.storm.crawler.bolt.SiteMapParserBolt</span>;</td>
      </tr>
      <tr>
        <td id="L23" class="blob-num js-line-number" data-line-number="23"></td>
        <td id="LC23" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">com.digitalpebble.storm.crawler.bolt.URLPartitionerBolt</span>;</td>
      </tr>
      <tr>
        <td id="L24" class="blob-num js-line-number" data-line-number="24"></td>
        <td id="LC24" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">com.digitalpebble.storm.crawler.indexing.StdOutIndexer</span>;</td>
      </tr>
      <tr>
        <td id="L25" class="blob-num js-line-number" data-line-number="25"></td>
        <td id="LC25" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">com.digitalpebble.storm.crawler.persistence.StdOutStatusUpdater</span>;</td>
      </tr>
      <tr>
        <td id="L26" class="blob-num js-line-number" data-line-number="26"></td>
        <td id="LC26" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">com.digitalpebble.storm.crawler.spout.MemorySpout</span>;</td>
      </tr>
      <tr>
        <td id="L27" class="blob-num js-line-number" data-line-number="27"></td>
        <td id="LC27" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L28" class="blob-num js-line-number" data-line-number="28"></td>
        <td id="LC28" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">backtype.storm.metric.LoggingMetricsConsumer</span>;</td>
      </tr>
      <tr>
        <td id="L29" class="blob-num js-line-number" data-line-number="29"></td>
        <td id="LC29" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">backtype.storm.topology.TopologyBuilder</span>;</td>
      </tr>
      <tr>
        <td id="L30" class="blob-num js-line-number" data-line-number="30"></td>
        <td id="LC30" class="blob-code blob-code-inner js-file-line"><span class="pl-k">import</span> <span class="pl-smi">backtype.storm.tuple.Fields</span>;</td>
      </tr>
      <tr>
        <td id="L31" class="blob-num js-line-number" data-line-number="31"></td>
        <td id="LC31" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L32" class="blob-num js-line-number" data-line-number="32"></td>
        <td id="LC32" class="blob-code blob-code-inner js-file-line"><span class="pl-c">/**</span></td>
      </tr>
      <tr>
        <td id="L33" class="blob-num js-line-number" data-line-number="33"></td>
        <td id="LC33" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> * Dummy topology to play with the spouts and bolts</span></td>
      </tr>
      <tr>
        <td id="L34" class="blob-num js-line-number" data-line-number="34"></td>
        <td id="LC34" class="blob-code blob-code-inner js-file-line"><span class="pl-c"> */</span></td>
      </tr>
      <tr>
        <td id="L35" class="blob-num js-line-number" data-line-number="35"></td>
        <td id="LC35" class="blob-code blob-code-inner js-file-line"><span class="pl-k">public</span> <span class="pl-k">class</span> <span class="pl-en">CrawlTopology</span> <span class="pl-k">extends</span> <span class="pl-e">ConfigurableTopology</span> {</td>
      </tr>
      <tr>
        <td id="L36" class="blob-num js-line-number" data-line-number="36"></td>
        <td id="LC36" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L37" class="blob-num js-line-number" data-line-number="37"></td>
        <td id="LC37" class="blob-code blob-code-inner js-file-line">    <span class="pl-k">public</span> <span class="pl-k">static</span> <span class="pl-k">void</span> <span class="pl-en">main</span>(<span class="pl-k">String</span>[] <span class="pl-v">args</span>) <span class="pl-k">throws</span> <span class="pl-smi">Exception</span> {</td>
      </tr>
      <tr>
        <td id="L38" class="blob-num js-line-number" data-line-number="38"></td>
        <td id="LC38" class="blob-code blob-code-inner js-file-line">        <span class="pl-smi">ConfigurableTopology</span><span class="pl-k">.</span>start(<span class="pl-k">new</span> <span class="pl-smi">CrawlTopology</span>(), args);</td>
      </tr>
      <tr>
        <td id="L39" class="blob-num js-line-number" data-line-number="39"></td>
        <td id="LC39" class="blob-code blob-code-inner js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L40" class="blob-num js-line-number" data-line-number="40"></td>
        <td id="LC40" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L41" class="blob-num js-line-number" data-line-number="41"></td>
        <td id="LC41" class="blob-code blob-code-inner js-file-line">    <span class="pl-k">@Override</span></td>
      </tr>
      <tr>
        <td id="L42" class="blob-num js-line-number" data-line-number="42"></td>
        <td id="LC42" class="blob-code blob-code-inner js-file-line">    <span class="pl-k">protected</span> <span class="pl-k">int</span> <span class="pl-en">run</span>(<span class="pl-k">String</span>[] <span class="pl-v">args</span>) {</td>
      </tr>
      <tr>
        <td id="L43" class="blob-num js-line-number" data-line-number="43"></td>
        <td id="LC43" class="blob-code blob-code-inner js-file-line">        <span class="pl-smi">TopologyBuilder</span> builder <span class="pl-k">=</span> <span class="pl-k">new</span> <span class="pl-smi">TopologyBuilder</span>();</td>
      </tr>
      <tr>
        <td id="L44" class="blob-num js-line-number" data-line-number="44"></td>
        <td id="LC44" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L45" class="blob-num js-line-number" data-line-number="45"></td>
        <td id="LC45" class="blob-code blob-code-inner js-file-line">        builder<span class="pl-k">.</span>setSpout(<span class="pl-s"><span class="pl-pds">&quot;</span>spout<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">MemorySpout</span>());</td>
      </tr>
      <tr>
        <td id="L46" class="blob-num js-line-number" data-line-number="46"></td>
        <td id="LC46" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L47" class="blob-num js-line-number" data-line-number="47"></td>
        <td id="LC47" class="blob-code blob-code-inner js-file-line">        builder<span class="pl-k">.</span>setBolt(<span class="pl-s"><span class="pl-pds">&quot;</span>partitioner<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">URLPartitionerBolt</span>())</td>
      </tr>
      <tr>
        <td id="L48" class="blob-num js-line-number" data-line-number="48"></td>
        <td id="LC48" class="blob-code blob-code-inner js-file-line">                .shuffleGrouping(<span class="pl-s"><span class="pl-pds">&quot;</span>spout<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L49" class="blob-num js-line-number" data-line-number="49"></td>
        <td id="LC49" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L50" class="blob-num js-line-number" data-line-number="50"></td>
        <td id="LC50" class="blob-code blob-code-inner js-file-line">        builder<span class="pl-k">.</span>setBolt(<span class="pl-s"><span class="pl-pds">&quot;</span>fetch<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">FetcherBolt</span>())<span class="pl-k">.</span>fieldsGrouping(</td>
      </tr>
      <tr>
        <td id="L51" class="blob-num js-line-number" data-line-number="51"></td>
        <td id="LC51" class="blob-code blob-code-inner js-file-line">                <span class="pl-s"><span class="pl-pds">&quot;</span>partitioner<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">Fields</span>(<span class="pl-s"><span class="pl-pds">&quot;</span>key<span class="pl-pds">&quot;</span></span>));</td>
      </tr>
      <tr>
        <td id="L52" class="blob-num js-line-number" data-line-number="52"></td>
        <td id="LC52" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L53" class="blob-num js-line-number" data-line-number="53"></td>
        <td id="LC53" class="blob-code blob-code-inner js-file-line">        builder<span class="pl-k">.</span>setBolt(<span class="pl-s"><span class="pl-pds">&quot;</span>sitemap<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">SiteMapParserBolt</span>())</td>
      </tr>
      <tr>
        <td id="L54" class="blob-num js-line-number" data-line-number="54"></td>
        <td id="LC54" class="blob-code blob-code-inner js-file-line">                .localOrShuffleGrouping(<span class="pl-s"><span class="pl-pds">&quot;</span>fetch<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L55" class="blob-num js-line-number" data-line-number="55"></td>
        <td id="LC55" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L56" class="blob-num js-line-number" data-line-number="56"></td>
        <td id="LC56" class="blob-code blob-code-inner js-file-line">        builder<span class="pl-k">.</span>setBolt(<span class="pl-s"><span class="pl-pds">&quot;</span>parse<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">JSoupParserBolt</span>())<span class="pl-k">.</span>localOrShuffleGrouping(</td>
      </tr>
      <tr>
        <td id="L57" class="blob-num js-line-number" data-line-number="57"></td>
        <td id="LC57" class="blob-code blob-code-inner js-file-line">                <span class="pl-s"><span class="pl-pds">&quot;</span>sitemap<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L58" class="blob-num js-line-number" data-line-number="58"></td>
        <td id="LC58" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L59" class="blob-num js-line-number" data-line-number="59"></td>
        <td id="LC59" class="blob-code blob-code-inner js-file-line">        builder<span class="pl-k">.</span>setBolt(<span class="pl-s"><span class="pl-pds">&quot;</span>index<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">StdOutIndexer</span>())<span class="pl-k">.</span>localOrShuffleGrouping(</td>
      </tr>
      <tr>
        <td id="L60" class="blob-num js-line-number" data-line-number="60"></td>
        <td id="LC60" class="blob-code blob-code-inner js-file-line">                <span class="pl-s"><span class="pl-pds">&quot;</span>parse<span class="pl-pds">&quot;</span></span>);</td>
      </tr>
      <tr>
        <td id="L61" class="blob-num js-line-number" data-line-number="61"></td>
        <td id="LC61" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L62" class="blob-num js-line-number" data-line-number="62"></td>
        <td id="LC62" class="blob-code blob-code-inner js-file-line">        <span class="pl-c">// can also use MemoryStatusUpdater for simple recursive crawls</span></td>
      </tr>
      <tr>
        <td id="L63" class="blob-num js-line-number" data-line-number="63"></td>
        <td id="LC63" class="blob-code blob-code-inner js-file-line">        builder<span class="pl-k">.</span>setBolt(<span class="pl-s"><span class="pl-pds">&quot;</span>status<span class="pl-pds">&quot;</span></span>, <span class="pl-k">new</span> <span class="pl-smi">StdOutStatusUpdater</span>())</td>
      </tr>
      <tr>
        <td id="L64" class="blob-num js-line-number" data-line-number="64"></td>
        <td id="LC64" class="blob-code blob-code-inner js-file-line">                .localOrShuffleGrouping(<span class="pl-s"><span class="pl-pds">&quot;</span>fetch<span class="pl-pds">&quot;</span></span>, <span class="pl-smi">Constants</span><span class="pl-k">.</span><span class="pl-smi">StatusStreamName</span>)</td>
      </tr>
      <tr>
        <td id="L65" class="blob-num js-line-number" data-line-number="65"></td>
        <td id="LC65" class="blob-code blob-code-inner js-file-line">                .localOrShuffleGrouping(<span class="pl-s"><span class="pl-pds">&quot;</span>sitemap<span class="pl-pds">&quot;</span></span>, <span class="pl-smi">Constants</span><span class="pl-k">.</span><span class="pl-smi">StatusStreamName</span>)</td>
      </tr>
      <tr>
        <td id="L66" class="blob-num js-line-number" data-line-number="66"></td>
        <td id="LC66" class="blob-code blob-code-inner js-file-line">                .localOrShuffleGrouping(<span class="pl-s"><span class="pl-pds">&quot;</span>parse<span class="pl-pds">&quot;</span></span>, <span class="pl-smi">Constants</span><span class="pl-k">.</span><span class="pl-smi">StatusStreamName</span>)</td>
      </tr>
      <tr>
        <td id="L67" class="blob-num js-line-number" data-line-number="67"></td>
        <td id="LC67" class="blob-code blob-code-inner js-file-line">                .localOrShuffleGrouping(<span class="pl-s"><span class="pl-pds">&quot;</span>index<span class="pl-pds">&quot;</span></span>, <span class="pl-smi">Constants</span><span class="pl-k">.</span><span class="pl-smi">StatusStreamName</span>);</td>
      </tr>
      <tr>
        <td id="L68" class="blob-num js-line-number" data-line-number="68"></td>
        <td id="LC68" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L69" class="blob-num js-line-number" data-line-number="69"></td>
        <td id="LC69" class="blob-code blob-code-inner js-file-line">        conf<span class="pl-k">.</span>registerMetricsConsumer(<span class="pl-smi">LoggingMetricsConsumer</span><span class="pl-k">.</span>class);</td>
      </tr>
      <tr>
        <td id="L70" class="blob-num js-line-number" data-line-number="70"></td>
        <td id="LC70" class="blob-code blob-code-inner js-file-line">
</td>
      </tr>
      <tr>
        <td id="L71" class="blob-num js-line-number" data-line-number="71"></td>
        <td id="LC71" class="blob-code blob-code-inner js-file-line">        <span class="pl-k">return</span> submit(<span class="pl-s"><span class="pl-pds">&quot;</span>crawl<span class="pl-pds">&quot;</span></span>, conf, builder);</td>
      </tr>
      <tr>
        <td id="L72" class="blob-num js-line-number" data-line-number="72"></td>
        <td id="LC72" class="blob-code blob-code-inner js-file-line">    }</td>
      </tr>
      <tr>
        <td id="L73" class="blob-num js-line-number" data-line-number="73"></td>
        <td id="LC73" class="blob-code blob-code-inner js-file-line">}</td>
      </tr>
</table>

  </div>

</div>

<a href="#jump-to-line" rel="facebox[.linejump]" data-hotkey="l" style="display:none">Jump to Line</a>
<div id="jump-to-line" style="display:none">
  <!-- </textarea> --><!-- '"` --><form accept-charset="UTF-8" action="" class="js-jump-to-line-form" method="get"><div style="margin:0;padding:0;display:inline"><input name="utf8" type="hidden" value="&#x2713;" /></div>
    <input class="linejump-input js-jump-to-line-field" type="text" placeholder="Jump to line&hellip;" aria-label="Jump to line" autofocus>
    <button type="submit" class="btn">Go</button>
</form></div>

  </div>
  <div class="modal-backdrop"></div>
</div>

    </div>
  </div>

    </div>

        <div class="container">
  <div class="site-footer" role="contentinfo">
    <ul class="site-footer-links right">
        <li><a href="https://status.github.com/" data-ga-click="Footer, go to status, text:status">Status</a></li>
      <li><a href="https://developer.github.com" data-ga-click="Footer, go to api, text:api">API</a></li>
      <li><a href="https://training.github.com" data-ga-click="Footer, go to training, text:training">Training</a></li>
      <li><a href="https://shop.github.com" data-ga-click="Footer, go to shop, text:shop">Shop</a></li>
        <li><a href="https://github.com/blog" data-ga-click="Footer, go to blog, text:blog">Blog</a></li>
        <li><a href="https://github.com/about" data-ga-click="Footer, go to about, text:about">About</a></li>
        <li><a href="https://github.com/pricing" data-ga-click="Footer, go to pricing, text:pricing">Pricing</a></li>

    </ul>

    <a href="https://github.com" aria-label="Homepage">
      <span aria-hidden="true" class="mega-octicon octicon-mark-github" title="GitHub "></span>
</a>
    <ul class="site-footer-links">
      <li>&copy; 2016 <span title="0.13117s from github-fe130-cp1-prd.iad.github.net">GitHub</span>, Inc.</li>
        <li><a href="https://github.com/site/terms" data-ga-click="Footer, go to terms, text:terms">Terms</a></li>
        <li><a href="https://github.com/site/privacy" data-ga-click="Footer, go to privacy, text:privacy">Privacy</a></li>
        <li><a href="https://github.com/security" data-ga-click="Footer, go to security, text:security">Security</a></li>
        <li><a href="https://github.com/contact" data-ga-click="Footer, go to contact, text:contact">Contact</a></li>
        <li><a href="https://help.github.com" data-ga-click="Footer, go to help, text:help">Help</a></li>
    </ul>
  </div>
</div>



    
    
    

    <div id="ajax-error-message" class="flash flash-error">
      <span aria-hidden="true" class="octicon octicon-alert"></span>
      <button type="button" class="flash-close js-flash-close js-ajax-error-dismiss" aria-label="Dismiss error">
        <span aria-hidden="true" class="octicon octicon-x"></span>
      </button>
      Something went wrong with that request. Please try again.
    </div>


      <script crossorigin="anonymous" integrity="sha256-ZSErq0jgJG8fk+N32pvDLUmXK1/0ug2Slk/8usPyF24=" src="https://assets-cdn.github.com/assets/frameworks-65212bab48e0246f1f93e377da9bc32d49972b5ff4ba0d92964ffcbac3f2176e.js"></script>
      <script async="async" crossorigin="anonymous" integrity="sha256-CvFxyblsJD/HM+Kgc5V2/FSf2QaHdFDxffUKsYidoow=" src="https://assets-cdn.github.com/assets/github-0af171c9b96c243fc733e2a0739576fc549fd906877450f17df50ab1889da28c.js"></script>
      
      
      
    <div class="js-stale-session-flash stale-session-flash flash flash-warn flash-banner hidden">
      <span aria-hidden="true" class="octicon octicon-alert"></span>
      <span class="signed-in-tab-flash">You signed in with another tab or window. <a href="">Reload</a> to refresh your session.</span>
      <span class="signed-out-tab-flash">You signed out in another tab or window. <a href="">Reload</a> to refresh your session.</span>
    </div>
  </body>
</html>

