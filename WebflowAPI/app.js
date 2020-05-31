//8d297ff3e80a957fdd9880e56b639cec2235a23028224958a6222a4247281a15
const Webflow = require('webflow-api');

// Initialize the API
const api = new Webflow({ token: '8d297ff3e80a957fdd9880e56b639cec2235a23028224958a6222a4247281a15' });

// Fetch a site
api.site({ siteId: '580e63e98c9a982ac9b8b741' }).then(site => console.log(site));