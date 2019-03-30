# -*- coding: utf-8 -*- #
# Copyright 2015 Google Inc. All Rights Reserved.
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
"""Command for abandoning instances owned by a managed instance group."""

from __future__ import absolute_import
from __future__ import division
from __future__ import unicode_literals

from googlecloudsdk.api_lib.compute import base_classes
from googlecloudsdk.api_lib.compute import instance_groups_utils
from googlecloudsdk.calliope import arg_parsers
from googlecloudsdk.calliope import base
from googlecloudsdk.command_lib.compute import flags
from googlecloudsdk.command_lib.compute import scope as compute_scope
from googlecloudsdk.command_lib.compute.instance_groups import flags as instance_groups_flags


class AbandonInstances(base.Command):
  """Abandon instances owned by a managed instance group."""

  @staticmethod
  def Args(parser):
    parser.display_info.AddFormat("""
        table(project(),
              zone(),
              selfLink.basename():label=INSTANCE,
              status)""")
    parser.add_argument('--instances',
                        type=arg_parsers.ArgList(min_length=1),
                        metavar='INSTANCE',
                        required=True,
                        help='Names of instances to abandon.')
    instance_groups_flags.MULTISCOPE_INSTANCE_GROUP_MANAGER_ARG.AddArgument(
        parser)

  def Run(self, args):
    holder = base_classes.ComputeApiHolder(self.ReleaseTrack())
    client = holder.client

    resource_arg = instance_groups_flags.MULTISCOPE_INSTANCE_GROUP_MANAGER_ARG
    default_scope = compute_scope.ScopeEnum.ZONE
    scope_lister = flags.GetDefaultScopeLister(client)
    igm_ref = resource_arg.ResolveAsResource(
        args,
        holder.resources,
        default_scope=default_scope,
        scope_lister=scope_lister)
    instances = instance_groups_utils.CreateInstanceReferences(
        holder.resources, client, igm_ref, args.instances)

    if igm_ref.Collection() == 'compute.instanceGroupManagers':
      field_name = 'instanceGroupManagersAbandonInstancesRequest'
      service = client.apitools_client.instanceGroupManagers
      requests = instance_groups_utils.SplitInstancesInRequest(
          client.messages.ComputeInstanceGroupManagersAbandonInstancesRequest(
              instanceGroupManager=igm_ref.Name(),
              instanceGroupManagersAbandonInstancesRequest=
              client.messages.InstanceGroupManagersAbandonInstancesRequest(
                  instances=instances),
              project=igm_ref.project,
              zone=igm_ref.zone), field_name)
    elif igm_ref.Collection() == 'compute.regionInstanceGroupManagers':
      field_name = 'regionInstanceGroupManagersAbandonInstancesRequest'
      service = client.apitools_client.regionInstanceGroupManagers
      requests = instance_groups_utils.SplitInstancesInRequest(
          client.messages.
          ComputeRegionInstanceGroupManagersAbandonInstancesRequest(
              instanceGroupManager=igm_ref.Name(),
              regionInstanceGroupManagersAbandonInstancesRequest=client.
              messages.RegionInstanceGroupManagersAbandonInstancesRequest(
                  instances=instances),
              project=igm_ref.project,
              region=igm_ref.region,), field_name)
    else:
      raise ValueError('Unknown reference type {0}'.format(
          igm_ref.Collection()))

    requests = instance_groups_utils.GenerateRequestTuples(
        service, 'AbandonInstances', requests)

    return instance_groups_utils.MakeRequestsList(client, requests, field_name)


AbandonInstances.detailed_help = {
    'brief': 'Abandon instances owned by a managed instance group.',
    'DESCRIPTION': """
        *{command}* abandons one or more instances from a managed instance
group, thereby reducing the targetSize of the group. Once instances have been
abandoned, the currentSize of the group is automatically reduced as well to
reflect the change.

Abandoning an instance does not reboot or delete the underlying virtual machine
instances, but just removes the instances from the instance group. If you would
like the delete the underlying instances, use the `delete-instances` command
instead.

For a more detailed overview of how abandoning instances from a managed instance
group works, refer to this section:
https://cloud.google.com/compute/docs/instance-groups/creating-groups-of-managed-instances#abandoning_instances
""",
}
