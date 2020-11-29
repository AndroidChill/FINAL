#!/usr/bin/env python3

import speech_recognition as sr

def get_text(path):
    #return path
    sample_audio = sr.AudioFile(path)
    recog = sr.Recognizer()
    with sample_audio as audio_file:
        recog.adjust_for_ambient_noise(audio_file)
        audio_content = recog.record(audio_file)

    return recog.recognize_google(audio_content)
