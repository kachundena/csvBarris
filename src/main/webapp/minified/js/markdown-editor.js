angular.module("angular-lightweight-markdown-editor-templates",[]).run(["$templateCache",function(t){t.put("angular-lightweight-markdown-template.html",'<div class="markdown-editor"><ul class="markdown-editor__actions"><li ng-click="markdownEditorCtrl.action(\'bold\')"><i class="ame-bold"></i></li><li ng-click="markdownEditorCtrl.action(\'italic\')"><i class="ame-italic"></i></li><li ng-click="markdownEditorCtrl.action(\'strikethrough\')"><i class="ame-strikethrough"></i></li><li class="separator"></li><li ng-click="markdownEditorCtrl.action(\'heading\')"><i class="ame-header"></i></li><li ng-click="markdownEditorCtrl.action(\'bullets\')"><i class="ame-list-ul"></i></li><li ng-click="markdownEditorCtrl.action(\'indent\')"><i class="ame-indent"></i></li><li ng-click="markdownEditorCtrl.action(\'code\')"><i class="ame-code"></i></li><li class="separator"></li><li ng-click="markdownEditorCtrl.action(\'link\')"><i class="ame-link"></i></li><li ng-click="markdownEditorCtrl.action(\'quote\')"><i class="ame-quote-right"></i></li><li class="separator"></li><li ng-click="markdownEditorCtrl.preview = !markdownEditorCtrl.preview" ng-show="markdownEditorCtrl.showdownEnabled" ng-class="{enabled: markdownEditorCtrl.preview}" class="preview">{{markdownEditorCtrl.translations.textPreview}}</li></ul><div class="markdown-editor__editorarea"><div ng-class="{nopreview: !markdownEditorCtrl.preview}"><textarea ng-model="markdownEditorCtrl.ngModel"></textarea></div><div class="markdown-editor__editorarea__preview" ng-if="markdownEditorCtrl.showdownEnabled" ng-show="markdownEditorCtrl.preview" ng-bind-html="markdownEditorCtrl.getHTML()"></div></div></div>')}]),function(){function t(){return{restrict:"E",templateUrl:"angular-lightweight-markdown-template.html",controller:r,controllerAs:"markdownEditorCtrl",scope:!0,bindToController:{ngModel:"=",textPreview:"@",textProvideText:"@",textProvideLink:"@"},require:["^form","ngModel"],link:function(t,r,i,n){e=r.find("textarea")[0];var a=(n[0],["name","required","minLength","maxLength","placeholder","selectionDirection","selectionStart","selectionEnd","spellcheck"]);angular.forEach(a,function(t){i[t]&&(e[t]=i[t])})}}}function r(t){this.preview=!1,this.showdownEnabled="undefined"!=typeof showdown;for(var r in a)angular.isDefined(this[r])&&(a[r]=this[r]);this.translations=a,this.action=function(t){var r=l[t](this.ngModel,n());r!==!1&&(this.ngModel=r)},this.getHTML=function(){return this.showdownEnabled?t.trustAsHtml(o.makeHtml(this.ngModel)):""}}function n(){return{start:e.selectionStart,end:e.selectionEnd,length:e.selectionEnd-e.selectionStart}}angular.module("angular-lightweight-markdown-editor",["ngSanitize","angular-lightweight-markdown-editor-templates"]).directive("markdownEditor",t);var e,a={textPreview:"Preview",textProvideText:"Please provide link text",textProvideLink:"Please provide link URL"};if("undefined"!=typeof showdown)var o=new showdown.Converter;r.$inject=["$sce"];var l={bold:function(t,r){return s.surraund(t,r.start,r.end-r.start,"**","**")},italic:function(t,r){return s.surraund(t,r.start,r.end-r.start,"*","*")},bullets:function(t,r){return s.startLinesWith(t,r.start,r.end,"* ")},heading:function(t,r){return s.startLinesWith(t,r.start,r.end,"#")},strikethrough:function(t,r){return s.surraund(t,r.start,r.end-r.start,"--","--")},indent:function(t,r){return s.startLinesWith(t,r.start,r.end,"	")},quote:function(t,r){return s.startLinesWith(t,r.start,r.end,"> ")},code:function(t,r){var i=t.substr(0,r.start).lastIndexOf("\n")+1,n=t.substr(r.end).indexOf("\n");if(-1==n)var e=t.length;else var e=r.end+n+1;return[t.substr(0,i),"```\n",t.substr(i,e-i),"\n```\n",t.substr(e)].join("")},link:function(t,r){if(r.length>0)var i=t.substr(r.start,r.length);else{var i=prompt(a.textProvideText);if(!i)return!1}var n=prompt(a.textProvideLink);return n?[t.substr(0,r.start),"["+i+"]","("+n+")",t.substr(r.end)].join(""):!1}},s={surraund:function(t,r,i,n,e){var a=t.substr(r,i);return[t.substr(0,r),n?n:"",a,e?e:"",t.substr(r+i)].join("")},startLinesWith:function(t,r,i,n){var e=s.indexes(t.substr(r,i-r),"\n",r),a=t.substr(0,r).lastIndexOf("\n")+1;t=[t.substr(0,a),n,t.substr(a)].join("");for(var o=0;o<e.length;o++)t=[t.substr(0,n.length*(o+1)+e[o]+1),n,t.substr(n.length*(o+1)+e[o]+1)].join("");return t},indexes:function(t,r,n){var e=[];for(i=0;i<t.length;++i)t.substring(i,i+r.length)==r&&e.push(i+n);return e}}}();