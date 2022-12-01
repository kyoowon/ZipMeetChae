// Discovery doc URL for APIs used by the quickstart
const DISCOVERY_DOC =
    "https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest";

const DISCOVERY_DOC_USER =
    "https://www.googleapis.com/discovery/v1/apis/people/v1/rest";

// Authorization scopes required by the API; multiple scopes can be
// included, separated by spaces.
const SCOPES = "https://www.googleapis.com/auth/calendar https://www.googleapis.com/auth/calendar.events https://www.googleapis.com/auth/contacts  https://www.googleapis.com/auth/contacts.readonly";

let gapiInited = false;
let gisInited = false;
let tokenClient = null;

async function gapiLoaded() {
    await gapi.load("client", initializeGapiClient);
}

async function gisLoaded() {
    tokenClient = await google.accounts.oauth2.initTokenClient({
        client_id: process.env.VUE_APP_GOOGLE_CLIENT_ID,
        scope: SCOPES,
        callback: "", // defined later
    });
    gisInited = true;
}

async function initializeGapiClient() {
    await gapi.client.init({
        //   apiKey: API_KEY,
        discoveryDocs: [DISCOVERY_DOC, DISCOVERY_DOC_USER],
    });
    gapiInited = true;
}

async function maybeEnableButtons() {
    await gapiLoaded();
    await gisLoaded();
    if (gapiInited && gisInited) {
        return true;
    }
}

/**
 *  Sign in the user upon button click.
 */
function AuthHandler() {
    tokenClient.callback = (resp) => {
        if (resp.error !== undefined) {
            throw resp;
        }
    };

    if (gapi.client.getToken() === null) {
        // Prompt the user to select a Google Account and ask for consent to share their data
        // when establishing a new session.
        tokenClient.requestAccessToken({
            prompt: "consent"
        });
    } else {
        // Skip display of account chooser and consent dialog for an existing session.
        tokenClient.requestAccessToken({
            prompt: ""
        });
    }
}

/**
 *  Sign out the user upon button click.
 */
function handleSignoutClick() {
    const token = gapi.client.getToken();
    console.log("token :", token);
    if (token !== null) {
        google.accounts.oauth2.revoke(token.access_token);
        gapi.client.setToken("");
    }
}


async function listUpcomingEvents() {
    let response;
    try {
        const request = {
            calendarId: "primary",
            timeMin: new Date().toISOString(),
            showDeleted: false,
            singleEvents: true,
            orderBy: "startTime",
        };
        response = await gapi.client.calendar.events.list(request);
    } catch (err) {
        return null;
    }

    const events = await response.result.items;
    if (!events || events.length == 0) {
        return "No events found.";
    }

    let result = [];
    // Flatten to string to display
    events.forEach(
        (str) => {
            result.push({
                str: str,
            })
        }
    );
    return result;
}
import moment from "moment";

async function createCalenderEvent(startTime, id) {
    let event = {
        "summary": "zipmeetchae",
        "location": "some text",
        "description": "zipmeetchae",
        "start": {
            "dateTime": moment(startTime).format("YYYY-MM-DDTHH:mm:ss+08:00"),
            "timeZone": "Asia/Seoul"
        },
        "end": {
            "dateTime": moment(startTime).format("YYYY-MM-DDTHH:mm:ss+08:00"),
            "timeZone": "Asia/Seoul"
        },
        "recurrence": [],
        "attendees": [{
            "email": `${id}`
        }],
        "reminders": {
            "useDefault": true
        },
        "conferenceData": {
            "createRequest": {
                "conferenceSolutionKey": {
                    "type": "hangoutsMeet"
                },
                "requestId": "somerequestid"
            }
        }
    };

    let request = await gapi.client.calendar.events.insert({
        'calendarId': 'primary',
        'conferenceDataVersion': 1,
        'resource': event
    });
    console.log(request);
    // request.execute(function (event) {
    //     appendPre('Event created: ' + event.htmlLink);
    // });

    return true;
}

async function listConnectionNames() {
    let response;
    try {
      // Fetch first 10 files
      response = await gapi.client.people.people.get({
        'resourceName': 'people/me',
        'personFields': 'names,emailAddresses',
      });
      return (response);
    } catch (err) {
      console.log(err.message);
      return;
    }
    // const connections = response.result.connections;
    // if (!connections || connections.length == 0) {
    //   console.log('No connections found.');
    //   return;
    // }
    // // Flatten to string to display
    // const output = connections.reduce(
    //     (str, person) => {
    //       if (!person.names || person.names.length === 0) {
    //         return `${str}Missing display name\n`;
    //       }
    //       return `${str}${person.names[0].displayName}\n`;
    //     },
    //     'Connections:\n');
    // console.log(output);
    // return output;
  }


export {
    listConnectionNames,
    maybeEnableButtons,
    AuthHandler,
    handleSignoutClick,
    listUpcomingEvents,
    createCalenderEvent
};