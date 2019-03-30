# -*- coding: utf-8 -*- #
# Copyright 2014 Google Inc. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
"""Command for describing target HTTPS proxies."""

from __future__ import absolute_import
from __future__ import division
from __future__ import unicode_literals

from googlecloudsdk.api_lib.compute import base_classes
from googlecloudsdk.calliope import base
from googlecloudsdk.command_lib.compute import flags as compute_flags
from googlecloudsdk.command_lib.compute.target_https_proxies import flags
from googlecloudsdk.command_lib.compute.target_https_proxies import target_https_proxies_utils


@base.ReleaseTracks(base.ReleaseTrack.GA, base.ReleaseTrack.BETA)
class Describe(base.DescribeCommand):
  """Display detailed information about a target HTTPS proxy.

    *{command}* displays all data associated with a target HTTPS proxy
  in a project.
  """

  TARGET_HTTPS_PROXY_ARG = None

  @staticmethod
  def Args(parser):
    Describe.TARGET_HTTPS_PROXY_ARG = flags.TargetHttpsProxyArgument()
    Describe.TARGET_HTTPS_PROXY_ARG.AddArgument(
        parser, operation_type='describe')

  def Run(self, args):
    holder = base_classes.ComputeApiHolder(self.ReleaseTrack())
    client = holder.client

    target_https_proxy_ref = self.TARGET_HTTPS_PROXY_ARG.ResolveAsResource(
        args,
        holder.resources,
        scope_lister=compute_flags.GetDefaultScopeLister(client))

    request = client.messages.ComputeTargetHttpsProxiesGetRequest(
        **target_https_proxy_ref.AsDict())

    return client.MakeRequests([(client.apitools_client.targetHttpsProxies,
                                 'Get', request)])[0]


@base.ReleaseTracks(base.ReleaseTrack.ALPHA)
class DescribeAlpha(Describe):
  """Display detailed information about a target HTTPS proxy.

  *{command}* displays all data associated with a target HTTPS proxy
  in a project.
  """

  TARGET_HTTPS_PROXY_ARG = None

  @classmethod
  def Args(cls, parser):
    cls.TARGET_HTTPS_PROXY_ARG = flags.TargetHttpsProxyArgument(
        include_alpha=True)
    cls.TARGET_HTTPS_PROXY_ARG.AddArgument(parser, operation_type='describe')

  def Run(self, args):
    holder = base_classes.ComputeApiHolder(self.ReleaseTrack())
    client = holder.client

    target_https_proxy_ref = self.TARGET_HTTPS_PROXY_ARG.ResolveAsResource(
        args,
        holder.resources,
        scope_lister=compute_flags.GetDefaultScopeLister(client))

    if target_https_proxies_utils.IsRegionalTargetHttpsProxiesRef(
        target_https_proxy_ref):
      request = client.messages.ComputeRegionTargetHttpsProxiesGetRequest(
          **target_https_proxy_ref.AsDict())
      collection = client.apitools_client.regionTargetHttpsProxies
    else:
      request = client.messages.ComputeTargetHttpsProxiesGetRequest(
          **target_https_proxy_ref.AsDict())
      collection = client.apitools_client.targetHttpsProxies

    return client.MakeRequests([(collection, 'Get', request)])[0]
