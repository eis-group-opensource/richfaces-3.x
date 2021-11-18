/**
 * editor_plugin_src.js
 *
 * Copyright 2009, Moxiecode Systems AB
 * Released under LGPL License.
 *
 * License: http://tinymce.moxiecode.com/license
 * Contributing: http://tinymce.moxiecode.com/contributing
 */

(function() {

	tinymce.create('tinymce.plugins.RSCWPlugin', {
		/**
		 * Initializes the plugin, this will be executed after the plugin has been created.
		 * This call is done before the editor instance has finished it's initialization so use the onInit event
		 * of the editor instance to intercept that event.
		 *
		 * @param {tinymce.Editor} ed Editor instance that the plugin is initialized in.
		 * @param {string} url Absolute URL to where the plugin is located.
		 */
		init : function(ed, url) {
			ed.addCommand('RSCW_checkSpelling', function() {
				if(typeof(RapidSpellCheckWeb)!="undefined"){
					RapidSpellCheckWeb.checkSpelling(true, [ed.id], function (tbElementName) {
						this.tbName = tbElementName;
						this.ignoreXML = true;
						this.getText = function() {
							return document.getElementById(this.tbName).value;
						};
						this.setText = function (text) {
							document.getElementById(this.tbName).value = text;
							tinyMCE.get(this.tbName).setContent(text);
						};
					});
				}else{
					alert("An error accured while seting up Spell Checker!\nSorry for inconvenience.");
				}
			});

			// Register rapidspellcheck button
			ed.addButton('rapidspellcheck', {
				title : 'Check Spelling',
				cmd : 'RSCW_checkSpelling',
				image : url + '/img/rapidspellcheck.gif'
			});

			// Add a node change handler, selects the button in the UI when a image is selected
			ed.onNodeChange.add(function(ed, cm, n) {
				cm.setActive('rapidspellcheck', n.nodeName == 'IMG');
			});
		},

		/**
		 * Creates control instances based in the incomming name. This method is normally not
		 * needed since the addButton method of the tinymce.Editor class is a more easy way of adding buttons
		 * but you sometimes need to create more complex controls like listboxes, split buttons etc then this
		 * method can be used to create those.
		 *
		 * @param {String} n Name of the control to create.
		 * @param {tinymce.ControlManager} cm Control manager to use inorder to create new control.
		 * @return {tinymce.ui.Control} New control instance or null if no control was created.
		 */
		createControl : function(n, cm) {
			return null;
		},

		/**
		 * Returns information about the plugin as a name/value array.
		 * The current keys are longname, author, authorurl, infourl and version.
		 *
		 * @return {Object} Name/value array containing information about the plugin.
		 */
		getInfo : function() {
			return {
				longname : 'Spell Check',
				author : 'Dalius.Sidlauskas@exigenservices.com',
				authorurl : 'https://wiki.exigenservices.com/display/IPB/Rapid+Spell+Check+plugin+%28Tiny+MCE%29',
				infourl : 'https://wiki.exigenservices.com/display/IPB/Rapid+Spell+Check+plugin+%28Tiny+MCE%29',
				version : "1.0"
			};
		}
	});

	// Register plugin
	tinymce.PluginManager.add('rapidspellcheck', tinymce.plugins.RSCWPlugin);
})();