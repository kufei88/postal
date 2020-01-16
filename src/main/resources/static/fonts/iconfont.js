;(function(window) {

var svgSprite = '<svg>' +
  ''+
    '<symbol id="icon-comment" viewBox="0 0 1024 1024">'+
      ''+
      '<path d="M924.10843 845.477071c47.986921 0 99.218746-35.712363 99.218746-83.874269L1023.327176 145.69015c0-48.089251-51.231825-98.736768-99.218746-98.736768L91.902618 46.953382C43.828716 46.953382 0.020978 97.599875 0.020978 145.69015l0 615.912651c0 48.160883 43.807738 83.874269 91.88164 83.874269l126.33943 0 106.962104 159.100578 97.874121-159.100578L924.10843 845.477071z"  ></path>'+
      ''+
    '</symbol>'+
  ''+
    '<symbol id="icon-save" viewBox="0 0 1024 1024">'+
      ''+
      '<path d="M958.708971 283.844673 902.580626 228.015134 419.975097 708.054211 121.41835 411.085636 65.290005 466.915176 363.846752 763.883751 363.800703 763.9298 419.929048 819.759339 419.975097 819.71329 420.021146 819.759339 476.149491 763.9298 476.103442 763.883751Z"  ></path>'+
      ''+
    '</symbol>'+
  ''+
    '<symbol id="icon-leixing" viewBox="0 0 1024 1024">'+
      ''+
      '<path d="M65.710584 139.544173c0-40.727587 32.606629-73.741491 73.741491-73.741491l234.970546 0c40.72554 0 73.740468 32.606629 73.740468 73.741491l0 234.970546c0 40.72554-32.606629 73.740468-73.740468 73.740468L139.452075 448.255187c-40.727587 0-73.741491-32.606629-73.741491-73.740468L65.710584 139.544173 65.710584 139.544173zM140.075269 649.485281l0 234.971569c0-0.14224-0.481977-0.624217-0.623193-0.624217l234.970546 0c-0.14224 0-0.624217 0.488117-0.624217 0.624217L373.798405 649.485281c0 0.14224 0.488117 0.624217 0.624217 0.624217L139.452075 650.109498C139.593292 650.109498 140.075269 649.627521 140.075269 649.485281L140.075269 649.485281zM65.710584 649.485281c0-40.72554 32.606629-73.740468 73.741491-73.740468l234.970546 0c40.72554 0 73.740468 32.606629 73.740468 73.740468l0 234.971569c0 40.72554-32.606629 73.740468-73.740468 73.740468L139.452075 958.197318c-40.727587 0-73.741491-32.606629-73.741491-73.740468L65.710584 649.485281 65.710584 649.485281zM650.0174 139.544173l0 234.970546c0-0.14224-0.481977-0.624217-0.624217-0.624217l234.971569 0c-0.14224 0-0.624217 0.488117-0.624217 0.624217L883.740536 139.544173c0 0.141216 0.488117 0.623193 0.624217 0.623193l-234.971569 0C649.535423 140.167367 650.0174 139.685389 650.0174 139.544173L650.0174 139.544173zM575.652715 139.544173c0-40.727587 32.606629-73.741491 73.740468-73.741491l234.971569 0c40.72554 0 73.740468 32.606629 73.740468 73.741491l0 234.970546c0 40.72554-32.606629 73.740468-73.740468 73.740468l-234.971569 0c-40.72554 0-73.740468-32.606629-73.740468-73.740468L575.652715 139.544173 575.652715 139.544173zM650.0174 649.485281l0 234.971569c0-0.14224-0.481977-0.624217-0.624217-0.624217l234.971569 0c-0.14224 0-0.624217 0.488117-0.624217 0.624217L883.740536 649.485281c0 0.14224 0.488117 0.624217 0.624217 0.624217l-234.971569 0C649.535423 650.109498 650.0174 649.627521 650.0174 649.485281L650.0174 649.485281zM575.652715 649.485281c0-40.72554 32.606629-73.740468 73.740468-73.740468l234.971569 0c40.72554 0 73.740468 32.606629 73.740468 73.740468l0 234.971569c0 40.72554-32.606629 73.740468-73.740468 73.740468l-234.971569 0c-40.72554 0-73.740468-32.606629-73.740468-73.740468L575.652715 649.485281 575.652715 649.485281 575.652715 649.485281zM575.652715 649.485281"  ></path>'+
      ''+
    '</symbol>'+
  ''+
    '<symbol id="icon-like" viewBox="0 0 1024 1024">'+
      ''+
      '<path d="M706.47832 107.23021c-65.959248 0-139.538033 51.930743-194.487529 107.029642C455.310883 159.160953 384.006907 107.505479 315.77285 107.505479c-66.741054 0-125.58423 19.697621-174.575014 68.019163-102.099353 100.527554-102.099353 263.551488 0 364.134301 29.1499 28.762067 349.374132 377.109824 370.485963 377.109824 21.11183 0 367.821273-367.371018 371.128599-370.677321 102.08605-100.596116 102.08605-263.62005 0-364.147604C832.146461 132.011616 775.507486 107.23021 706.47832 107.23021z"  ></path>'+
      ''+
    '</symbol>'+
  ''+
    '<symbol id="icon-wenzhang" viewBox="0 0 1024 1024">'+
      ''+
      '<path d="M56.888889 1024 56.888889 0 967.111111 0 967.111111 1024 56.888889 1024ZM796.444444 227.555556 227.555556 227.555556 227.555556 341.333333 796.444444 341.333333 796.444444 227.555556ZM796.444444 455.111111 227.555556 455.111111 227.555556 568.888889 796.444444 568.888889 796.444444 455.111111ZM796.444444 682.666667 227.555556 682.666667 227.555556 796.444444 796.444444 796.444444 796.444444 682.666667Z"  ></path>'+
      ''+
    '</symbol>'+
  ''+
'</svg>'
var script = function() {
    var scripts = document.getElementsByTagName('script')
    return scripts[scripts.length - 1]
  }()
var shouldInjectCss = script.getAttribute("data-injectcss")

/**
 * document ready
 */
var ready = function(fn){
  if(document.addEventListener){
      document.addEventListener("DOMContentLoaded",function(){
          document.removeEventListener("DOMContentLoaded",arguments.callee,false)
          fn()
      },false)
  }else if(document.attachEvent){
     IEContentLoaded (window, fn)
  }

  function IEContentLoaded (w, fn) {
      var d = w.document, done = false,
      // only fire once
      init = function () {
          if (!done) {
              done = true
              fn()
          }
      }
      // polling for no errors
      ;(function () {
          try {
              // throws errors until after ondocumentready
              d.documentElement.doScroll('left')
          } catch (e) {
              setTimeout(arguments.callee, 50)
              return
          }
          // no errors, fire

          init()
      })()
      // trying to always fire before onload
      d.onreadystatechange = function() {
          if (d.readyState == 'complete') {
              d.onreadystatechange = null
              init()
          }
      }
  }
}

/**
 * Insert el before target
 *
 * @param {Element} el
 * @param {Element} target
 */

var before = function (el, target) {
  target.parentNode.insertBefore(el, target)
}

/**
 * Prepend el to target
 *
 * @param {Element} el
 * @param {Element} target
 */

var prepend = function (el, target) {
  if (target.firstChild) {
    before(el, target.firstChild)
  } else {
    target.appendChild(el)
  }
}

function appendSvg(){
  var div,svg

  div = document.createElement('div')
  div.innerHTML = svgSprite
  svg = div.getElementsByTagName('svg')[0]
  if (svg) {
    svg.setAttribute('aria-hidden', 'true')
    svg.style.position = 'absolute'
    svg.style.width = 0
    svg.style.height = 0
    svg.style.overflow = 'hidden'
    prepend(svg,document.body)
  }
}

if(shouldInjectCss && !window.__iconfont__svg__cssinject__){
  window.__iconfont__svg__cssinject__ = true
  try{
    document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>");
  }catch(e){
    console && console.log(e)
  }
}

ready(appendSvg)


})(window)
